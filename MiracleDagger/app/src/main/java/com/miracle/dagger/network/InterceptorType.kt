package com.miracle.dagger.network

import javax.inject.Qualifier

@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class InterceptorType(val value: String = "")
