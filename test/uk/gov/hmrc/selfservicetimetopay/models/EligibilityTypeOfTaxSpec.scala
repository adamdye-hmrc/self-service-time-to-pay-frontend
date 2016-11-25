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

package uk.gov.hmrc.selfservicetimetopay.models

import uk.gov.hmrc.play.test.UnitSpec

class EligibilityTypeOfTaxSpec extends UnitSpec {

  "EligibilityTypeOfTax" should {

    "create with parameters" in {
      val edt = EligibilityTypeOfTax(hasSelfAssessmentDebt = true, hasOtherDebt = true)
      edt.hasOtherDebt should be (true)
      edt.hasSelfAssessmentDebt should be (true)
    }

    "create with no parameters" in {
      val edt = EligibilityTypeOfTax()
      edt.hasOtherDebt should be(false)
      edt.hasSelfAssessmentDebt should be(false)
    }
  }
}