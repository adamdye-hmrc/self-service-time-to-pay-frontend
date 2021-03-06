/*
 * Copyright 2017 HM Revenue & Customs
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

package uk.gov.hmrc.selfservicetimetopay.forms

import java.time.{DateTimeException, LocalDate}
import java.util.Calendar

import play.api.data.Form
import play.api.data.Forms._
import play.api.data.validation.{Constraint, Invalid, Valid, ValidationError}
import uk.gov.hmrc.selfservicetimetopay.models.CalculatorAmountDue.MaxCurrencyValue
import uk.gov.hmrc.selfservicetimetopay.models._

import scala.util.Try
import scala.util.control.Exception.catching

object CalculatorForm {
  val dueByYearMax = Calendar.getInstance().get(Calendar.YEAR) + 1
  val dueByYearMin = 1996
  val dueByMonthMin = 1
  val dueByMonthMax = 12
  val dueByDayMin = 1
  val dueByDayMax = 31

  def tryToInt(input: String) = {
    catching(classOf[NumberFormatException]) opt input.toInt
  }

  def isInt(input: String) = {
    tryToInt(input).nonEmpty
  }

  val dueByDateValidation = tuple(
    "dueByDay" -> text
      .verifying("ssttp.calculator.form.what-you-owe-date.due_by.not-valid-day", { i: String =>hasValue(i) & !i.contains(".")})
      .verifying("ssttp.calculator.form.what-you-owe-date.due_by.not-valid-day-number", { i => i.contains(".") ||  i.isEmpty || (i.nonEmpty && isInt(i))})
       .verifying("ssttp.calculator.form.what-you-owe-date.due_by.not-valid-day-number", { i => !isInt(i) || (isInt(i) && (i.toInt <= dueByDayMax) && (i.toInt >= dueByDayMin)) }),
    "dueByMonth" -> text
      .verifying("ssttp.calculator.form.what-you-owe-date.due_by.not-valid-month", { i: String => hasValue(i) & !i.contains(".")})
      .verifying("ssttp.calculator.form.what-you-owe-date.due_by.not-valid-month-number", { i =>i.contains(".") ||  i.isEmpty || (i.nonEmpty && isInt(i))})
      .verifying("ssttp.calculator.form.what-you-owe-date.due_by.not-valid-month-number", { i => !isInt(i) || (isInt(i) && (i.toInt <= dueByMonthMax) && (i.toInt >= dueByMonthMin)) }),
    "dueByYear" -> text
      .verifying("ssttp.calculator.form.what-you-owe-date.due_by.not-valid-year", { i: String =>hasValue(i) })
      .verifying("ssttp.calculator.form.what-you-owe-date.due_by.not-valid-year", { i => i.isEmpty || (i.nonEmpty && isInt(i)) })
      .verifying("ssttp.calculator.form.what-you-owe-date.due_by.not-valid-year-too-high", { i => !isInt(i) || (isInt(i) && (i.toInt <= dueByYearMax)) })
      .verifying("ssttp.calculator.form.what-you-owe-date.due_by.not-valid-year-too-low", { i => !isInt(i) || (isInt(i) && (i.toInt >= dueByYearMin)) })
  )
  def hasValue(textBox:String):Boolean ={
    (textBox != null) && textBox.nonEmpty
  }


  def dateValidator: Constraint[(String, String, String)] =
    Constraint[(String, String, String)]("ssttp.calculator.form.what-you-owe.due_by.not-valid-date") { data =>
      if (data._1.isEmpty || data._2.isEmpty || data._3.isEmpty) {
        Valid
      } else {
        catching(classOf[DateTimeException]).opt(LocalDate.of(tryToInt(data._3).get, tryToInt(data._2).get, tryToInt(data._1).get)) match {
          case d: Some[LocalDate] => Valid
          case _ => Invalid(ValidationError("ssttp.calculator.form.what-you-owe.due_by.not-valid-date"))
        }
      }
    }



  case class RemoveItem(index: Int)

  val removeAmountDueForm = Form(single("index" -> number))

  def createPaymentTodayForm(totalDue: BigDecimal): Form[CalculatorPaymentToday] = {
    Form(mapping(
      "amount" -> text
        .verifying("ssttp.calculator.form.payment_today.amount.required.min", { i: String =>  if (i.nonEmpty && Try(BigDecimal(i)).isSuccess &&  BigDecimal(i).scale <= 2 ) BigDecimal(i) >= 0.00 else true})
        .verifying("ssttp.calculator.form.payment_today.amount.required", { i => Try(BigDecimal(i)).isSuccess})
        .verifying("ssttp.calculator.form.payment_today.amount.decimal-places", { i =>
          if (Try(BigDecimal(i)).isSuccess) BigDecimal(i).scale <= 2 else true})
        .verifying("ssttp.calculator.form.payment_today.amount.less-than-owed", i =>
          if (i.nonEmpty && Try(BigDecimal(i)).isSuccess) BigDecimal(i) < totalDue else true)
        .verifying("ssttp.calculator.form.payment_today.amount.less-than-maxval", { i: String =>
          if (i.nonEmpty && Try(BigDecimal(i)).isSuccess) BigDecimal(i) < MaxCurrencyValue else true
        })
    )(text => CalculatorPaymentToday(text))(bd => Some(bd.amount.toString)))
  }

  def createDebitDateForm(): Form[DebitDueDate] = {
    Form(mapping(
      "dueBy" -> dueByDateValidation.verifying(dateValidator)
    )((date: (String, String, String)) =>
      DebitDueDate(date._1, date._2, date._3))
    ((amountDue: DebitDueDate) =>
      Some((amountDue.dueByYear, amountDue.dueByMonth, amountDue.dueByDay)))
    )
  }

  def createSinglePaymentForm(): Form[CalculatorSinglePayment] = {
    Form(mapping(
      "amount" -> text
        .verifying("ssttp.calculator.form.what-you-owe-amount.amount.required", { i: String =>  Try(BigDecimal(i)).isSuccess})
        .verifying("ssttp.calculator.form.what-you-owe-amount.amount.required.min", { i: String =>  if (i.nonEmpty && Try(BigDecimal(i)).isSuccess && BigDecimal(i).scale <= 2) BigDecimal(i) >= 0.01 else true})
        .verifying("ssttp.calculator.form.what-you-owe-amount.amount.less-than-maxval", { i: String =>
          if (i.nonEmpty && Try(BigDecimal(i)).isSuccess) BigDecimal(i) < MaxCurrencyValue else true
        })
        .verifying("ssttp.calculator.form.what-you-owe-amount.amount.decimal-places", { i =>
          if (Try(BigDecimal(i)).isSuccess) BigDecimal(i).scale <= 2 else true})
    )(text => CalculatorSinglePayment(text))(bd => Some(bd.amount.toString)))
  }

  val minMonths = 2
  val maxMonths = 11

  val durationForm = createDurationForm()

  def createDurationForm(): Form[CalculatorDuration] = {
    def greaterThan: Constraint[String] = Constraint[String]("constraint.duration-more-than") { o =>
      if (tryToInt(o).fold(false)(_ < minMonths)) Invalid(ValidationError("ssttp.calculator.form.duration.months.greater-than", minMonths)) else Valid
    }

    def lessThan: Constraint[String] = Constraint[String]("constraint.duration-more-than") { o =>
      if (tryToInt(o).fold(false)(_ > maxMonths)) Invalid(ValidationError("ssttp.calculator.form.duration.months.less-than", maxMonths)) else Valid
    }

    Form(mapping("months" -> text
      .verifying("ssttp.calculator.form.duration.months.required", months => isInt(months))
      .verifying(greaterThan, lessThan)
    )(months => CalculatorDuration.apply(tryToInt(months)))(calculatorDuration => calculatorDuration.months.map(m => m.toString)))
  }

  val payTodayForm: Form[PayTodayQuestion] = Form(mapping(
    "paytoday" -> optional(boolean).verifying("ssttp.calculator.form.payment_today_question.required", _.nonEmpty)
  )(PayTodayQuestion.apply)(PayTodayQuestion.unapply))

}
