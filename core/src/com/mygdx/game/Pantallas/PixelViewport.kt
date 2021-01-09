package com.mygdx.game.Pantallas

import com.badlogic.gdx.graphics.Camera
import com.badlogic.gdx.graphics.OrthographicCamera
import com.badlogic.gdx.utils.viewport.Viewport
import kotlin.math.max
import kotlin.math.min

/** Created by Darius on 09-Jan-21. */
class PixelScreenViewport(var pixelsWidth: Float, var pixelsHeight: Float, camera: Camera? = OrthographicCamera())
	: Viewport() {
	init {
		setCamera(camera)
	}

	override fun update(screenWidth: Int, screenHeight: Int, centerCamera: Boolean) {
		val factor = max(min(screenHeight / pixelsHeight, screenWidth / pixelsWidth), 1f)

		// todo make the calculations to allow limited extra "room to grow" in X and Y, and letterboxing after that
		val viewportWidth = max(pixelsWidth * factor, screenWidth.toFloat())
		val viewportHeight = max(pixelsHeight * factor, screenHeight.toFloat())

		worldWidth = (viewportWidth / factor).toFloat()
		worldHeight = (viewportHeight / factor).toFloat()

		// Center.
		setScreenBounds(
				((screenWidth - viewportWidth) / 2).toInt(),
				((screenHeight - viewportHeight) / 2).toInt(),
				viewportWidth.toInt(),
				viewportHeight.toInt()
		)

		apply(centerCamera)
	}

	override fun apply(centerCamera: Boolean) {
//		HdpiUtils.glViewport(screenX, screenY, screenWidth, screenHeight)
		camera.viewportWidth = worldWidth
		camera.viewportHeight = worldHeight
		if (centerCamera) camera.position[worldWidth / 2, worldHeight / 2] = 0f
		camera.update()
	}
}