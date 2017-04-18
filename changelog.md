
* __further improved layout of spinner buttons__

    [Luke Smith](mailto:luke.smith@digital.hmrc.gov.uk) - Mon, 20 Mar 2017 13:54:07 +0000
    
    

* __Increased space between account number and dd confirmation__

    [Luke Smith](mailto:luke.smith@digital.hmrc.gov.uk) - Mon, 20 Mar 2017 12:16:28 +0000
    
    

* __Updated payment schedule tezt size and spacing for v5__

    [Luke Smith](mailto:luke.smith@digital.hmrc.gov.uk) - Mon, 20 Mar 2017 11:47:56 +0000
    
    

* __Updated message text for direct debit schedule__

    [Luke Smith](mailto:luke.smith@digital.hmrc.gov.uk) - Mon, 20 Mar 2017 10:53:31 +0000
    
    

* __Improved layout of calculator page__

    [Luke Smith](mailto:luke.smith@digital.hmrc.gov.uk) - Mon, 20 Mar 2017 10:29:47 +0000
    
    Added custom css to improve the layout of the data on the calculator page, so
    it isn&#39;t as widely spaced. Moved the breakdown section out of the grid layout
    subsection it was in so that it can expand across the width of the page.
    

* __Changed account name box to match protoype__

    [colm.cavanagh](mailto:colm.cavanagh@digital.hmrc.gov.uk) - Mon, 20 Mar 2017 10:17:03 +0000
    
    

* __updated auth calculator page to match v5 of prototype__

    [Luke Smith](mailto:luke.smith@digital.hmrc.gov.uk) - Mon, 20 Mar 2017 09:58:38 +0000
    
    

* __Added custom css for + and - button layout__

    [Luke Smith](mailto:luke.smith@digital.hmrc.gov.uk) - Fri, 17 Mar 2017 17:31:31 +0000
    
    Custom css was required for the + and - buttons on the calculator in order to
    make them display correctly.
    

* __SSTTP-1150 and STTP-1154 (#221)__

    [Colm Cavanagh](mailto:colm.m.cavanagh@gmail.com) - Fri, 17 Mar 2017 16:49:58 +0000
    
    * Updated instalment summary page and deleted unused messages
     Changed print button message to correct text
    
    * Updated html as per prototype for the application complete page
     Updated instalment plan summary and removed section padding set to zero in css
    
     Added in test for banner on application complete page
     Added in custom css for gap in instalment summary
     removed space between header and banner and changed message on application
    complete page
    
    * Updated feedback button text on application complete page
    
    * cleaned up imports in application_complete and instalment_plan_summary
    
    * removed unused button and fixed messages file
    
    * added in deleted message and moved hard coding style to css
    
    * Fixed direct debit message and removed redundant css
    

* __SSTTP 1313 - Upgrade to frontend bootstrap version (#230)__

    [Luke Tebbs](mailto:hmrclt@users.noreply.github.com) - Fri, 17 Mar 2017 16:23:20 +0000
    
    * SSTTP-1313 Update config for new version of bootstrap
    
    * SSTTP-1313 Bumped version up to apply fix to redirect
    

* __Added custom CSS class for the payment today form on the calculator page__

    [Nick Karaolis](mailto:nick.karaolis@digital.hmrc.gov.uk) - Fri, 17 Mar 2017 16:22:42 +0000
    
    

* __SSTTP-1184 - Calculator input will be reset upon sign in (#229)__

    [Colm Cavanagh](mailto:colm.m.cavanagh@gmail.com) - Fri, 17 Mar 2017 16:15:08 +0000
    
    * Calculator input will be reset upon sign in
    
    * rewrote the session put to use flat map instead to avoid race conditions
    
    * Updated sessionCache put in submitAmountOwed to make sure future is completed
    before redirect
    
    * Changed format of methods and fixed broken test
     calculator controller test was broken as a sessionCache.put was
    not being
    mocked. This didn&#39;t exist before due to a race condition,
    where the put was
    not being waited for before a redirect occurred.
    

* __Updated the layout of non-auth calculator page__

    [Luke Smith](mailto:luke.smith@digital.hmrc.gov.uk) - Fri, 17 Mar 2017 15:20:28 +0000
    
    Changed the layout of the non-auth calculator page to be a closer match to the
    v5 prototype. The +and - buttons still need a layout change and the initial
    payment input field needs to be repositioned.
    

* __Colm/ssttp 1151/change enter your bank details page (#226)__

    [Colm Cavanagh](mailto:colm.m.cavanagh@gmail.com) - Fri, 17 Mar 2017 13:59:15 +0000
    
    * enter bank details page now visually matches prototype
    
    * Updated banking details with correct validation and visuals for sort code
    boxes
    
    * changed submit direct debit so correct values will be filled in when errors
    occur
    
    * Updated direct debit form so only one error message is shown
    

* __SSTTP-1145 Fixed incorrect routing for payment today (#227)__

    [Colm Cavanagh](mailto:colm.m.cavanagh@gmail.com) - Thu, 16 Mar 2017 15:28:32 +0000
    
    Back link on calculator page changes depending on initial payment
     Removed unused schedule from pattern matching

* __Application complete print button message updated__

    [Colm Cavanagh](mailto:colm.m.cavanagh@gmail.com) - Thu, 16 Mar 2017 14:46:33 +0000
    
    

* __SSTTP-1149 remove redundant misalignment bullet point messages__

    [Luke Smith](mailto:luke.smith@digital.hmrc.gov.uk) - Tue, 14 Mar 2017 10:10:27 +0000
    
    

* __SSTTP-1153 - Update direct debit confirmation page to match v5 of the prototype (#224)__

    [Luke Smith](mailto:lukesmudgesmith@gmail.com) - Tue, 14 Mar 2017 09:57:15 +0000
    
    Added custom css to remove the massive spacing added when using assets frontend
    grid layout.
    Edited in-page header to be h2 rather than h3
    Increased the text
    size of bank and payment plan details, whilst making direct debit
    information
    smaller

* __Reformatted case statement in call us method to be more concise__

    [Nick Karaolis](mailto:nick.karaolis@digital.hmrc.gov.uk) - Mon, 13 Mar 2017 15:42:33 +0000
    
    

* __Removed parentheses from the call us method Added loggedIn parameter for first case in call us method__

    [Nick Karaolis](mailto:nick.karaolis@digital.hmrc.gov.uk) - Mon, 13 Mar 2017 15:21:28 +0000
    
    

* __Updated the call us page to use the phone number based on different scenarios outlined in SSTTP-1146. Changed the you need to file phone number to meet new requirements.__

    [Nick Karaolis](mailto:nick.karaolis@digital.hmrc.gov.uk) - Mon, 13 Mar 2017 15:15:41 +0000
    
    

* __Colm/ssttp-1152/bank not found page remove (#222)__

    [Colm Cavanagh](mailto:colm.m.cavanagh@gmail.com) - Mon, 13 Mar 2017 08:57:54 +0000
    
    * Removed bank details error page and updated error messages for direct debit
    form
    
    * Fixed form so it will repopulate fields when a bank not found error occurs
    
    * removed method banksListValidated
    
    * removed unused method ValidateOrRetrieveAccounts and removed tests with it

* __Added in extra &#39; in isn&#39;t__

    [colm.cavanagh](mailto:colm.cavanagh@digital.hmrc.gov.uk) - Fri, 10 Mar 2017 12:06:03 +0000
    
    

* __Added in &#39; to messages removed accidental dublication__

    [colm.cavanagh](mailto:colm.cavanagh@digital.hmrc.gov.uk) - Fri, 10 Mar 2017 09:46:27 +0000
    
    

* __SSTTP-1147: Updated heading css to add margin at top of headers for better spacing. Updated headers on what you owe date and amount pages__

    [Nick Karaolis](mailto:nick.karaolis@digital.hmrc.gov.uk) - Fri, 10 Mar 2017 08:45:17 +0000
    
    

* __Removed unused messages__

    [colm.cavanagh](mailto:colm.cavanagh@digital.hmrc.gov.uk) - Thu, 9 Mar 2017 17:06:22 +0000
    
    

* __Updated misalignment page as per prototype__

    [colm.cavanagh](mailto:colm.cavanagh@digital.hmrc.gov.uk) - Thu, 9 Mar 2017 16:44:55 +0000
    
    

* __Change account number text input size (#218)__

    [Colm Cavanagh](mailto:colm.m.cavanagh@gmail.com) - Thu, 9 Mar 2017 15:19:15 +0000
    
    

* __removed now from how much can you afford to pay__

    [colm.cavanagh](mailto:colm.cavanagh@digital.hmrc.gov.uk) - Thu, 9 Mar 2017 14:59:41 +0000
    
    


