package com.example;

/**
 * Created by 小黄 on 2017/10/16.
 */

import com.google.auto.service.AutoService;
import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.TypeSpec;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Filer;
import javax.annotation.processing.Messager;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.Processor;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.Modifier;
import javax.lang.model.element.TypeElement;
import javax.lang.model.util.Elements;

@AutoService(Processor.class)
public class ShareProcessor extends AbstractProcessor {
    private Messager messager;
    private Filer filer;
    private Elements elements;

    @Override
    public synchronized void init(ProcessingEnvironment processingEnv) {
        super.init(processingEnv);
        messager = processingEnv.getMessager();
        elements = processingEnv.getElementUtils();      // Get class meta.
        filer = processingEnv.getFiler();
    }

    @Override
    public Set<String> getSupportedAnnotationTypes() {
        Set<String> ret = new HashSet<>();
        ret.add(WXAppId.class.getCanonicalName());
        return ret;
    }

    @Override
    public SourceVersion getSupportedSourceVersion() {
        return SourceVersion.latestSupported();
    }

    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {

        if (annotations.isEmpty()) {
            return false;
        }
        TypeElement payActivity = elements.getTypeElement("com.littleyellow.wxapi.WXEntryActivity2");
        String applicationId = null;
        Set<? extends Element> payAppIds = roundEnv.getElementsAnnotatedWith(WXAppId.class);
        if (payAppIds != null && payAppIds.size() > 0) {
            Element modules = payAppIds.iterator().next();
            applicationId = modules.getAnnotation(WXAppId.class).value();
        }
        if (applicationId == null || applicationId.length() == 0) return false;

        TypeSpec payEntryActivity = TypeSpec.classBuilder("WXEntryActivity")
                .addModifiers(Modifier.PUBLIC)
                .superclass(ClassName.get(payActivity))
                .build();
        try {
            JavaFile.builder(applicationId + ".wxapi", payEntryActivity)
                    .build()
                    .writeTo(filer);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }
}
