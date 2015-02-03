// Generated code from Butter Knife. Do not modify!
package org.magnum.videoup.client;

import android.view.View;
import butterknife.ButterKnife.Finder;

public class LoginScreenActivity$$ViewInjector {
  public static void inject(Finder finder, final org.magnum.videoup.client.LoginScreenActivity target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131230733, "field 'password_'");
    target.password_ = (android.widget.EditText) view;
    view = finder.findRequiredView(source, 2131230732, "field 'userName_'");
    target.userName_ = (android.widget.EditText) view;
    view = finder.findRequiredView(source, 2131230735, "field 'server_'");
    target.server_ = (android.widget.EditText) view;
    view = finder.findRequiredView(source, 2131230734, "method 'login'");
    view.setOnClickListener(
      new android.view.View.OnClickListener() {
        @Override public void onClick(
          android.view.View p0
        ) {
          target.login();
        }
      });
  }

  public static void reset(org.magnum.videoup.client.LoginScreenActivity target) {
    target.password_ = null;
    target.userName_ = null;
    target.server_ = null;
  }
}
