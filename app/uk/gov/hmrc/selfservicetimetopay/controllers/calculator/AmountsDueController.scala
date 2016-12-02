/*
 * Copyright 2016 HM Revenue & Customs
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package uk.gov.hmrc.selfservicetimetopay.controllers.calculator

import play.api.mvc._
import uk.gov.hmrc.selfservicetimetopay.config.TimeToPayController
import uk.gov.hmrc.selfservicetimetopay.forms.CalculatorForm
import uk.gov.hmrc.selfservicetimetopay.models.{CalculatorAmountsDue, TTPSubmission}
import uk.gov.hmrc.selfservicetimetopay.modelsFormat._
import views.html.selfservicetimetopay.calculator._

class AmountsDueController extends TimeToPayController {

  def start: Action[AnyContent] = Action { request =>
    Redirect(routes.AmountsDueController.getAmountsDue())
  }

  def getAmountsDue: Action[AnyContent] = Action.async { implicit request =>
    sessionCache.get.map {
      case Some(ttpSubmission) => ttpSubmission.manualDebits
      case None => IndexedSeq.empty
    }.map(data => Ok(amounts_due_form.render(CalculatorAmountsDue(data), CalculatorForm.amountDueForm, request)))
  }

  def submitAddAmountDue: Action[AnyContent] = Action.async { implicit request =>
    CalculatorForm.amountDueForm.bindFromRequest().fold(
      formWithErrors =>
        sessionCache.get.map {
          case Some(ttpSubmission) => ttpSubmission.manualDebits
          case None => IndexedSeq.empty
        }.map(data =>  BadRequest(amounts_due_form.render(CalculatorAmountsDue(data), formWithErrors, request))),

      validFormData => {
        sessionCache.get.map {
          case Some(ttpSubmission) =>
            ttpSubmission.copy(manualDebits = ttpSubmission.manualDebits :+ validFormData)
          case None => TTPSubmission(manualDebits = IndexedSeq(validFormData))
        }.map { ttpData =>
          sessionCache.put(ttpData)
        }.map { _ =>
          Redirect(routes.AmountsDueController.getAmountsDue())
        }
      }
    )
  }

  def submitRemoveAmountDue: Action[AnyContent] = Action.async { implicit request =>
    val itemToRemove = CalculatorForm.amountDueForm.bindFromRequest()
    sessionCache.get.map {
      case Some(ttpSubmission) => ttpSubmission.copy(manualDebits = ttpSubmission.manualDebits.filter(_!=itemToRemove.value.get))
      case None => TTPSubmission(manualDebits = IndexedSeq.empty)
    }.map { ttpData =>sessionCache.put(ttpData)
    }.map { _ => Redirect(routes.AmountsDueController.getAmountsDue())}
  }

  def submitAmountsDue: Action[AnyContent] = Action.async { implicit request =>
    sessionCache.get.map {
      case Some(ttpSubmission) => ttpSubmission.manualDebits
      case None => IndexedSeq.empty
    }.map { ttpData =>
      if(ttpData.isEmpty) BadRequest(amounts_due_form.render(CalculatorAmountsDue(IndexedSeq.empty),
        CalculatorForm.amountDueForm.withGlobalError("You need to add at least one amount due"), request))
      else Redirect(uk.gov.hmrc.selfservicetimetopay.controllers.routes.CalculatorController.getPaymentToday())
    }
  }
}