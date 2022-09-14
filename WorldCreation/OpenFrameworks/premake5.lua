project "OpenFrameworks"
    kind "StaticLib"
    language "C++"
    staticruntime "off"
    runtime "Debug"

    targetdir ("../bin/" .. outputdir .. "/%{prj.name}")
    objdir ("../bin-int/" .. outputdir .. "/%{prj.name}")

    files
    {
        "Src/3d/of3dPrimitives.h",
        "Src/3d/of3dUtils.h",
        "Src/3d/ofCamera.h",
        "Src/3d/ofEasyCam.h",
        "Src/3d/ofMesh.h",
        "Src/3d/ofNode.h",
        "Src/app/ofAppBaseWindow.h",
        "Src/app/ofAppGLFWWindow.h",
        "Src/app/ofAppNoWindow.h",
        "Src/app/ofBaseApp.h",
        "Src/app/ofMainLoop.h",
        "Src/app/ofWindowSettings.h",
        "Src/events/ofEvent.h",
        "Src/gl/ofBufferObject.h",
        "Src/gl/ofFbo.h",
        "Src/gl/ofGLBaseTypes.h",
        "Src/gl/ofGLRenderer.h",
        "Src/gl/ofGLUtils.h",
        "Src/gl/ofLight.h",
        "Src/gl/ofMaterial.h",
        "Src/gl/ofGLProgrammableRenderer.h",
        "Src/gl/ofShader.h",
        "Src/gl/ofTexture.h",
        "Src/gl/ofVbo.h",
        "Src/gl/ofVboMesh.h",
        "Src/graphics/of3dGraphics.h",
        "Src/graphics/ofBitmapFont.h",
        "Src/graphics/ofCairoRenderer.h",
        "Src/graphics/ofGraphics.h",
        "Src/graphics/ofGraphicsBaseTypes.h",
        "Src/graphics/ofGraphicsConstants.h",
        "Src/graphics/ofImage.h",
        "Src/graphics/ofPath.h",
        "Src/graphics/ofPixels.h",
        "Src/graphics/ofPolyline.h",
        "Src/graphics/ofRendererCollection.h",
        "Src/graphics/ofTessellator.h",
        "Src/graphics/ofTrueTypeFont.h",
        "Src/math/ofMath.h",
        "Src/math/ofMathConstants.h",
        "Src/math/ofMatrix3x3.h",
        "Src/math/ofMatrix4x4.h",
        "Src/math/ofQuaternion.h",
        "Src/math/ofVec2f.h",
        "Src/math/ofVec3f.h",
        "Src/math/ofVec4f.h",
        "Src/math/ofVectorMath.h",
        "Src/ofMain/h",
        "Src/app/ofAppRunner.h",
        "Src/sound/ofFmodSoundPlayer.h",
        "Src/sound/ofRtAudioSoundStream.h",
        "Src/sound/ofSoundBaseTypes.h",
        "Src/sound/ofSoundPlayer.h",
        "Src/sound/ofSoundStream.h",
        "Src/types/ofParameter.h",
        "Src/types/ofParameterGroup.h",
        "Src/types/ofColor.h",
        "Src/types/ofPoint.h",
        "Src/types/ofRectangle.h",
        "Src/types/ofTypes.h",
        "Src/utils/ofConstants.h",
        "Src/utils/ofFileUtils.h",
        "Src/utils/ofFpsCounter.h",
        "Src/utils/ofJson.h",
        "Src/utils/ofLog.h",
        "Src/utils/ofMatrixStack.h",
        "Src/utils/ofNoise.h",
        "Src/utils/ofSystemUtils.h",
        "Src/utils/ofThread.h",
        "Src/utils/ofThreadChannel.h",
        "Src/utils/ofTimer.h",
        "Src/utils/ofURLFileLoader.h",
        "Src/utils/ofUtils.h",
        "Src/utils/ofXml.h",
        "Src/video/ofDirectShowGrabber.h",
        "Src/video/ofDirectShowPlayer.h",
        "Src/video/ofVideoBaseTypes.h",
        "Src/video/ofVideoGrabber.h",
        "Src/video/ofVideoPlayer.h",
        "Src/communication/ofArduino.h",
        "Src/communication/ofSerial.h",
        "Src/events/ofEvents.h",
        "Src/events/ofEventUtils.h",
        "Src/3d/of3dPrimitives.cpp",
        "Src/3d/of3dUtils.cpp",
        "Src/3d/ofCamera.cpp",
        "Src/3d/ofEasyCam.cpp",
        "Src/3d/ofNode.cpp",
        "Src/app/ofAppGLFWWindow.cpp",
        "Src/app/ofAppNoWindow.cpp",
        "Src/app/ofAppRunner.cpp",
        "Src/app/ofBaseApp.cpp",
        "Src/app/ofMainLoop.cpp",
        "Src/events/ofEvents.cpp",
        "Src/gl/ofBufferObject.cpp",
        "Src/gl/ofFbo.cpp",
        "Src/gl/ofGLRenderer.cpp",
        "Src/gl/ofGLUtils.cpp",
        "Src/gl/ofLight.cpp",
        "Src/gl/ofMaterial.cpp",
        "Src/gl/ofGLProgrammableRenderer.cpp",
        "Src/gl/ofShader.cpp",
        "Src/gl/ofTexture.cpp",
        "Src/gl/ofVbo.cpp",
        "Src/gl/ofVboMesh.cpp",
        "Src/graphics/of3dGraphics.cpp",
        "Src/graphics/ofBitmapFont.cpp",
        "Src/graphics/ofCairoRenderer.cpp",
        "Src/graphics/ofGraphics.cpp",
        "Src/graphics/ofGraphicsBaseTypes.cpp",
        "Src/graphics/ofImage.cpp",
        "Src/graphics/ofPath.cpp",
        "Src/graphics/ofPixels.cpp",
        "Src/graphics/ofRendererCollection.cpp",
        "Src/graphics/ofTessellator.cpp",
        "Src/graphics/ofTrueTypeFont.cpp",
        "Src/math/ofMath.cpp",
        "Src/math/ofMatrix3x3.cpp",
        "Src/math/ofMatrix4x4.cpp",
        "Src/math/ofQuaternion.cpp",
        "Src/math/ofVec2f.cpp",
        "Src/math/ofVec4f.cpp",
        "Src/sound/ofFmodSoundPlayer.cpp",
        "Src/sound/ofRtAudioSoundStream.cpp",
        "Src/sound/ofSoundBaseTypes.cpp",
        "Src/sound/ofSoundBuffer.cpp",
        "Src/sound/ofSoundPlayer.cpp",
        "Src/sound/ofSoundStream.cpp",
        "Src/types/ofBaseTypes.cpp",
        "Src/types/ofColor.cpp",
        "Src/types/ofParameter.cpp",
        "Src/types/ofParameterGroup.cpp",
        "Src/types/ofRectangle.cpp",
        "Src/utils/ofFileUtils.cpp",
        "Src/utils/ofFpsCounter.cpp",
        "Src/utils/ofLog.cpp",
        "Src/utils/ofMatrixStack.cpp",
        "Src/utils/ofSystemUtils.cpp",
        "Src/utils/ofThread.cpp",
        "Src/utils/ofTimer.cpp",
        "Src/utils/ofURLFileLoader.cpp",
        "Src/utils/ofUtils.cpp",
        "Src/utils/ofXml.cpp",
        "Src/video/ofDirectShowGrabber.cpp",
        "Src/video/ofDirectShowPlayer.cpp",
        "Src/video/ofVideoGrabber.cpp",
        "Src/video/ofVideoPlayer.cpp",
        "Src/communication/ofArduino.cpp",
        "Src/communication/ofSerial.cpp",
    }

    includedirs
    {
        "Src/",
        "Src/graphics",
        "Src/app",
        "Src/sound",
        "Src/utils",
        "Src/communication",
        "Src/video",
        "Src/types",
        "Src/math",
        "Src/3d",
        "Src/gl",
        "Src/events",
        "Dependencies/glut/include",
        "Dependencies/glm/include",
        "Dependencies/rtAudio/include",
        "Dependencies/quicktime/include",
        "Dependencies/freetype/include",
        "Dependencies/freetype/include/freetype2",
        "Dependencies/freeImage/include",
        "Dependencies/fmod/include",
        "Dependencies/videoInput/include",
        "Dependencies/glew/include/",
        "Dependencies/glu/include",
        "Dependencies/tess2/include",
        "Dependencies/cairo/include/cairo",
        "Dependencies/poco/include",
        "Dependencies/glfw/include",
        "Dependencies/openssl/include",
        "Dependencies/utf8/include",
        "Dependencies/boost/include",
        "Dependencies/json/include",
        "Dependencies/curl/include",
        "Dependencies/uriparser/include",
        "Dependencies/pugixml/include",
    }

    filter "system:windows"
        cppdialect "C++14"
        staticruntime "On"
        systemversion "latest"

    filter "configurations:Debug"
        defines 
        {
            "WIN32",
            "CURL_STATICLIB",
            "_DEBUG",
            "_CONSOLE",
            "POCO_STATIC",
            "CAIRO_WIN32_STATIC_BUILD",
            "DISABLE_SOME_FLOATING_POINT",
        }
        symbols "On"
        buildoptions "/MDd"

    filter "configurations:Release"
        defines 
        {
            "WIN32",
            "CURL_STATICLIB",
            "_DEBUG",
            "_CONSOLE",
            "POCO_STATIC",
            "CAIRO_WIN32_STATIC_BUILD",
            "DISABLE_SOME_FLOATING_POINT",
        }
        optimize "On"

    filter "configurations:Dist"
        defines 
        {
            "WIN32",
            "CURL_STATICLIB",
            "_DEBUG",
            "_CONSOLE",
            "POCO_STATIC",
            "CAIRO_WIN32_STATIC_BUILD",
            "DISABLE_SOME_FLOATING_POINT",
        }
        symbols "On"