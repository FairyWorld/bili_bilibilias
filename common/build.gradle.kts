@Suppress("DSL_SCOPE_VIOLATION")
plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.hilt)
    alias(libs.plugins.ksp)
    alias(libs.plugins.kotlin)
    alias(libs.plugins.detekt)
    alias(libs.plugins.kotlin.serialization)
    kotlin("kapt")
}
apply {
    from("/../config.gradle")
}

ksp {
    arg("ModuleName", project.name)
}

android {
    namespace = "com.imcys.bilibilias.common"
    compileSdk = 34

    defaultConfig {
        minSdk = 21
        targetSdk = 33

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }

    }

    dataBinding {
        isEnabled = true
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    kotlinOptions {
        jvmTarget = "17"
    }
}
kotlin {
    jvmToolchain(17)
}

dependencies {

    // 深拷贝
    api(libs.deeprecopy.core)
    ksp(libs.deeprecopy.compiler)

    // hilt库，实现控制反转
    api(libs.hilt.android)
    kapt(libs.hilt.compiler)

    // 核心代码库
    api(libs.okdownload)

    // 提供kotlin extension，可以不引入
    api(libs.ktx)

    /**
     * SmoothRefreshLayout支持
     */
    api(libs.srl.core)
    api(libs.srl.ext.material) // md刷新头
    api(libs.srl.ext.classics)


    /**
     * MMKV 储存框架
     */
    api(libs.mmkv)


    /**
     * 伸缩布局
     */
    api(libs.flexbox)

    /**
     * jxl库
     * 直接生成excel文件时采用
     */
    api(libs.jxl)

    /**
     * 网络图片加载库
     * 专为compose打造
     */
    api(libs.coil.compose)

    /**
     * rv框架
     * 实现RV的动画效果
     */
    api(libs.brv)

    /**
     * 底部对话框库
     * 为AS专门打造适配的对话框库
     */
    api(libs.asBottomDialog)

    // 协程
    api(libs.kotlinx.coroutines.android)

    /**
     * RxFFmpeg
     * 支持视频合并等操作
     */
    api(libs.rxFFmpeg)

    /**
     * 组件化路由库
     */
    api(libs.kcomponent.rx)
    ksp(libs.kcomponent.compiler)

    // 百度统计
    api(libs.mtj.sdk.circle)

    // 开屏引导
    api(libs.hyy920109.guidePro)

    // 微软分发
    api(libs.appcenter.distribute)
    // 微软统计
    api(libs.appcenter.analytics)
    api(libs.appcenter.crashes)

    //api( "com.github.fondesa:recycler-view-divider:3.6.0" rv分割

    /**
     * room
     * 本地化数据库
     */
    api(libs.room.runtime)
    api(libs.androidx.room.ktx)
    ksp(libs.androidx.room.compiler)

    /**
     * 文件选择器
     */
    api(libs.androidFilePicker)

    /**
     * xutils
     * 下载文件支持
     */
    api(libs.xutils)

    /**
     * DanmakuFlameMaster
     * 烈焰弹幕使
     */
    api(libs.dfm)

    //饺子播放器
    api(libs.jiaozivideoplayer)

    //lottie动画库
    api(libs.lottie)

    //implementation "androidx.palette:palette:1.0.0"

    api(libs.banner)

    api(libs.glide)
    //implementation("jp.wasabeef:glide-transformations:4.3.0")

    /**
     * 沉浸式布局库
     */
    api(libs.uiTimateBarX)

    /**
     * ktor全局支持
     */
    api(libs.ktor.client.android)
    api(libs.ktor.client.okhttp)
    //日志
    api(libs.ktor.client.logging)//Logging
    //json解析支持
    api(libs.ktor.client.content.negotiation)
    api(libs.ktor.serialization.gson)
    api(libs.gson)
    api(libs.ktor.serialization.kotlinx.json)

    api(libs.constraintlayout)
    api(libs.androidx.lifecycle.viewmodel.ktx)
    api(libs.androidx.lifecycle.runtime.ktx)
    api(libs.androidx.preference.ktx)

    api(libs.activity.compose)
    api(platform(libs.compose.bom))
    api(libs.ui)
    api(libs.ui.graphics)
    api(libs.ui.tooling.preview)
    api(libs.material3)
    androidTestImplementation(platform(libs.compose.bom))

    api(libs.androidx.core.ktx)
    implementation(libs.appcompat)
    implementation(libs.material)
    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)
}

tasks.named("detekt", io.gitlab.arturbosch.detekt.Detekt::class).configure {
    reports {
        // Enable/Disable XML report (default: true)
        xml.required.set(true)
        xml.outputLocation.set(file("$projectDir/config/detekt.xml"))
        // Enable/Disable HTML report (default: true)
        html.required.set(true)
        html.outputLocation.set(file("$projectDir/config/detekt.html"))
        // Enable/Disable MD report (default: false)
        md.required.set(true)
        md.outputLocation.set(file("$projectDir/config/detekt.md"))
    }
}