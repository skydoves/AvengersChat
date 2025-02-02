/*
 * Copyright 2022 Stream.IO, Inc. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package io.getstream.avengerschat.core.uicomponents.binding

import android.view.View
import androidx.appcompat.widget.AppCompatImageView
import androidx.core.view.isVisible
import androidx.databinding.BindingAdapter
import androidx.lifecycle.findViewTreeLifecycleOwner
import coil.imageLoader
import coil.load
import coil.request.ImageRequest
import coil.transform.CircleCropTransformation
import com.skydoves.androidveil.VeilLayout
import io.getstream.chat.android.client.models.User
import io.getstream.chat.android.ui.avatar.AvatarView

object ViewBinding {
  @JvmStatic
  @BindingAdapter("loadImage")
  fun bindLoadImage(view: AppCompatImageView, url: String?) {
    view.load(url)
  }

  @JvmStatic
  @BindingAdapter("loadCircleImage")
  fun bindLoadCircleImage(view: AppCompatImageView, url: String?) {
    val request = ImageRequest.Builder(view.context)
      .data(url)
      .target(view)
      .transformations(CircleCropTransformation())
      .lifecycle(view.findViewTreeLifecycleOwner())
      .build()
    view.context.imageLoader.enqueue(request)
  }

  @JvmStatic
  @BindingAdapter("withVeil", "loadImageWithVeil")
  fun bindLoadImageWithVeil(view: AppCompatImageView, veilLayout: VeilLayout, url: String?) {
    val request = ImageRequest.Builder(view.context)
      .data(url)
      .target(view)
      .lifecycle(view.findViewTreeLifecycleOwner())
      .listener(
        onError = { _, _ -> veilLayout.unVeil() },
        onSuccess = { _, _ -> veilLayout.unVeil() }
      )
      .build()
    view.context.imageLoader.enqueue(request)
  }

  @JvmStatic
  @BindingAdapter("isGone")
  fun bindIsGone(view: View, isGone: Boolean) {
    view.isVisible = !isGone
  }

  @JvmStatic
  @BindingAdapter("user")
  fun bindUser(avatarView: AvatarView, user: User?) {
    user?.let { avatarView.setUserData(it) }
  }
}
