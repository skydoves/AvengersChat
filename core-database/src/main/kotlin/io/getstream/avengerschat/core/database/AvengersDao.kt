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

package io.getstream.avengerschat.core.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import io.getstream.avengerschat.core.database.entity.AvengerEntity

@Dao
interface AvengersDao {

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  suspend fun insertAvengers(posters: List<AvengerEntity>)

  @Query("SELECT * FROM AvengerEntity WHERE id = :id_")
  suspend fun getAvenger(id_: String): AvengerEntity

  @Query("SELECT * FROM AvengerEntity")
  suspend fun getAvengers(): List<AvengerEntity>
}
