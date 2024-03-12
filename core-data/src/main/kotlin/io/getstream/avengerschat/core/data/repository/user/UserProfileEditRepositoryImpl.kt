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

package io.getstream.avengerschat.core.data.repository.user

import androidx.annotation.WorkerThread
import io.getstream.avengerschat.core.model.Avenger
import io.getstream.avengerschat.core.network.AppDispatchers
import io.getstream.avengerschat.core.network.Dispatcher
import io.getstream.chat.android.client.ChatClient
import io.getstream.chat.android.client.utils.onSuccessSuspend
import io.getstream.chat.android.models.User
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import timber.log.Timber
import javax.inject.Inject

internal class UserProfileEditRepositoryImpl @Inject constructor(
  private val chatClient: ChatClient,
  @Dispatcher(AppDispatchers.IO) private val dispatcher: CoroutineDispatcher
) : UserProfileEditRepository {

  init {
    Timber.d("injection UserProfileEditRepository")
  }

  /**
   * Updates a specific user for updating the new profile image.
   */
  @WorkerThread
  override fun updateUser(avenger: Avenger, newProfileUrl: String) = flow {
    val user = User(
      id = avenger.id,
      name = avenger.name,
      image = newProfileUrl
    )
    val result = chatClient.updateUser(user).await()
    result.onSuccessSuspend {
      emit(it)
    }
  }.flowOn(dispatcher)
}
