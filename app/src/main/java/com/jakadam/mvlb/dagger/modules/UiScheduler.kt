package com.jakadam.mvlb.dagger.modules

import javax.inject.Qualifier

@Qualifier
@kotlin.annotation.Target(AnnotationTarget.ANNOTATION_CLASS, AnnotationTarget.FIELD, AnnotationTarget.FUNCTION)
@kotlin.annotation.Retention(AnnotationRetention.RUNTIME)
annotation class UiScheduler
