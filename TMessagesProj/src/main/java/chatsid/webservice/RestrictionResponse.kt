package chatsid.webservice

import com.google.gson.annotations.SerializedName

class RestrictionResponse {

    val group_join_limit: Int? = null

    val group_create_limit: Int? = null

    val channels_join_limit: Int? = null

    val channels_create_limit: Int? = null

    val private_send_photo: Int? = null

    val private_send_video: Int? = null

    val private_receive_photo: Int? = null

    val private_receive_video: Int? = null

    val group_send_photo: Int? = null

    val group_send_video: Int? = null

    val group_receive_photo: Int? = null

    val group_receive_video: Int? = null

    val allow_bots_install: Int? = null

    val allow_fully_encrypted_chat: Int? = null

    val allow_block_user: Int? = null

    val not_contacts: Int? = null

    val save_photo: Int? = null

    val stickers: Int? = null

    val multimedia_web: Boolean? = null

    val allow_avatars: Boolean? = null

    val api_url: String? = null

}