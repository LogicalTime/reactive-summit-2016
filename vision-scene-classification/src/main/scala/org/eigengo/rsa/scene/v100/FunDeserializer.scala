/*
 * The Reactive Summit Austin talk
 * Copyright (C) 2016 Jan Machacek
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 *
 */
package org.eigengo.rsa.scene.v100

import java.util

import org.apache.kafka.common.serialization.Deserializer

/**
  * Wraps ``fun`` into ``Deserializer[A]``, which can be conveniently used
  * in the Kafka client
  *
  * @param fun the (pure) deserialization function
  */
class FunDeserializer[A](fun: Array[Byte] ⇒ A) extends Deserializer[A] {

  override def configure(configs: util.Map[String, _], isKey: Boolean): Unit = { }

  override def close(): Unit = { }

  override def deserialize(topic: String, data: Array[Byte]): A = fun(data)
}
