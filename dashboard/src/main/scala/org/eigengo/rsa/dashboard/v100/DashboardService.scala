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
package org.eigengo.rsa.dashboard.v100

import akka.http.scaladsl.server.{Directives, PathMatchers, Route}
import akka.stream.scaladsl.{Flow, Sink, Source}
import org.eigengo.rsa.ScalaPBMarshalling

import scala.concurrent.ExecutionContext

trait DashboardService extends Directives with PathMatchers with ScalaPBMarshalling {

  def summarySource: Source[Summary, _]

  def dashboardRoute(implicit executionContext: ExecutionContext): Route = {
    path("dashboard") {
      get {
        handleWebSocketMessages(Flow.fromSinkAndSource(Sink.ignore, summarySource.map(x ⇒ marshalTextMessage(x))))
      }
    }
  }

}
