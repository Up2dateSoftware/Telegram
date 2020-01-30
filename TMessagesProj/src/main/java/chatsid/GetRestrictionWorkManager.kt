package chatsid

import android.content.Context
import androidx.work.RxWorker
import androidx.work.WorkerParameters
import chatsid.webservice.ChatSIDRxService
import io.reactivex.Single
import org.telegram.messenger.UserConfig

class GetRestrictionWorkManager(val context: Context, val params: WorkerParameters) : RxWorker(context, params) {

    private val chatSIDRxService = ChatSIDRxService()

    override fun createWork(): Single<Result> {
        return if (Restrictions.getInstance().restrictionItem != null) {
            val restrictionItem = Restrictions.getInstance().restrictionItem
            val user = UserConfig.getInstance(UserConfig.selectedAccount).currentUser
            var versionName = context.packageManager.getPackageInfo(context.packageName, 0).versionName
            chatSIDRxService.getRestriction(restrictionItem?.dns, user.phone, user.id, versionName)
                    .doOnSuccess {
                        it.allow_avatars?.let {
                            restrictionItem?.avatars = it
                        }

                        it.allow_block_user?.let {
                            restrictionItem?.block = it==1
                        }

                        it.allow_bots_install?.let {
                            restrictionItem?.bots = it==1
                        }

                        it.allow_fully_encrypted_chat?.let {
                            restrictionItem?.encrypted = it==1
                        }

                        it.api_url?.let {
                            restrictionItem?.dns = it
                        }

                        it.channels_create_limit?.let {
                            restrictionItem?.channelCreate = it
                        }

                        it.channels_join_limit?.let {
                            restrictionItem?.channelJoin = it
                        }

                        it.group_create_limit?.let {
                            restrictionItem?.groupCreate = it
                        }

                        it.group_join_limit?.let {
                            restrictionItem?.groupJoin = it
                        }

                        it.channels_create_limit?.let {
                            restrictionItem?.channelCreate = it
                        }

                        it.channels_join_limit?.let {
                            restrictionItem?.channelJoin = it
                        }

                        it.group_send_photo?.let {
                            restrictionItem?.group?.photoRestriction?.send = it==1
                        }

                        it.group_send_video?.let {
                            restrictionItem?.group?.videoRestriction?.send = it==1
                        }

                        it.group_receive_photo?.let {
                            restrictionItem?.group?.photoRestriction?.receive = it==1
                        }

                        it.group_receive_video?.let {
                            restrictionItem?.group?.videoRestriction?.receive = it==1
                        }

                        it.multimedia_web?.let {
                            restrictionItem?.multimedia = it
                        }

                        it.save_photo?.let {
                            restrictionItem?.savePhoto = it==1
                        }

                        it.not_contacts?.let {
                            restrictionItem?.notcontacts = it==1
                        }

                        it.stickers?.let {
                            restrictionItem?.stickers = it==1
                        }

                        it.private_send_photo?.let {
                            restrictionItem?.personal?.photoRestriction?.send = it==1
                        }

                        it.private_send_video?.let {
                            restrictionItem?.personal?.videoRestriction?.send = it==1
                        }

                        it.private_receive_photo?.let {
                            restrictionItem?.personal?.photoRestriction?.receive = it==1
                        }

                        it.private_receive_video?.let {
                            restrictionItem?.personal?.videoRestriction?.receive = it==1
                        }
                        restrictionItem?.let {
                            Restrictions.getInstance().updateRestriction(it)
                        }
                    }
                    .map { Result.success() }
                    .onErrorReturn {
                        Result.failure()
                    }
        } else {
            Single.just("one")
                    .doOnSuccess { }
                    .map { Result.success() }
                    .onErrorReturn { Result.failure() }
        }
    }
}