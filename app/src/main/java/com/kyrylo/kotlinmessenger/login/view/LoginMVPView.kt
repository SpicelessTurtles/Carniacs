package com.kyrylo.kotlinmessenger.login.view

import com.kyrylo.kotlinmessenger.base.view.MVPView

interface LoginMVPView : MVPView{
   fun performMainActivity()
   fun performRegisterActivity()
}