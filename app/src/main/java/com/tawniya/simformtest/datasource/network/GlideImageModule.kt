package com.tawniya.simformtest.datasource.network

import android.content.Context
import android.graphics.drawable.Drawable
import com.bumptech.glide.GlideBuilder
import com.bumptech.glide.annotation.GlideModule
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.module.AppGlideModule
import com.bumptech.glide.request.transition.DrawableCrossFadeFactory

@GlideModule
class GlideImageModule: AppGlideModule() {

    override fun applyOptions(context: Context, builder: GlideBuilder) {
        val factory =  DrawableCrossFadeFactory.Builder().setCrossFadeEnabled(true).build()
        builder.setDefaultTransitionOptions(Drawable::class.java, DrawableTransitionOptions.withCrossFade(factory))
    }

}