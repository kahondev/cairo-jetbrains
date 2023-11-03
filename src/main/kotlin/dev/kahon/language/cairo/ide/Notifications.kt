package dev.kahon.language.cairo.ide

import com.intellij.notification.Notification
import com.intellij.notification.NotificationGroupManager
import com.intellij.notification.NotificationType
import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.project.Project
import com.intellij.openapi.util.NlsContexts.NotificationContent
import com.intellij.openapi.util.NlsContexts.NotificationTitle


const val NOTIFICATION_GROUP_ID = "cairo.main"

@Suppress("UnstableApiUsage")
fun createNotification(
    notificationGroupId: String = NOTIFICATION_GROUP_ID,
    @NotificationTitle title: String = "",
    @NotificationContent content: String,
    type: NotificationType,
    vararg actions: AnAction,
): Notification = NotificationGroupManager
    .getInstance()
    .getNotificationGroup(notificationGroupId)
    .createNotification(title, content, type).
    apply {
        for (action in actions) {
            addAction(action)
        }
    }

@Suppress("UnstableApiUsage")
fun showNotification(
    project: Project,
    @NotificationTitle title: String = "",
    @NotificationContent content: String,
    type: NotificationType,
    vararg actions: AnAction,
) = createNotification(
    notificationGroupId = NOTIFICATION_GROUP_ID,
    title = title,
    content = content,
    type = type,
    actions = actions,
).notify(project)

@Suppress("UnstableApiUsage")
fun showNotification(
    project: Project,
    notificationGroupId: String,
    @NotificationTitle title: String = "",
    @NotificationContent content: String,
    type: NotificationType,
    vararg actions: AnAction,
) = createNotification(
    notificationGroupId = notificationGroupId,
    title = title,
    content = content,
    type = type,
    actions = actions,
).notify(project)