package cloud.mindbox.mindbox_android

import cloud.mindbox.mobile_sdk.inapp.presentation.InAppCallback
import io.flutter.plugin.common.BasicMessageChannel
import io.flutter.plugin.common.BinaryMessenger
import io.flutter.plugin.common.JSONMessageCodec

class MindboxInAppCallback(binaryMessenger: BinaryMessenger) : InAppCallback {

    private val chanel = BasicMessageChannel(
        binaryMessenger,
        "mindbox-in-app-push",
        JSONMessageCodec.INSTANCE,
    )

    override fun onInAppClick(id: String, redirectUrl: String, payload: String) {
        println("onInAppClick")
        chanel.send(
            mapOf(
                "name" to "open",
                "event" to mapOf(
                    "id" to id,
                    "redirectUrl" to redirectUrl,
                    "payload" to payload
                )
            )
        )
    }

    override fun onInAppDismissed(id: String) {
        println("onInAppDismissed")
        chanel.send(
            mapOf(
                "name" to "close",
                "event" to mapOf(
                    "id" to id,
                )
            )
        )
    }
}