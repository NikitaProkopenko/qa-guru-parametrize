package guru.qa.page;

import com.codeborne.selenide.Condition;
import guru.qa.domain.MenuItem;
import static com.codeborne.selenide.Selenide.$$;

public class GoogleResultPage {
    public GoogleResultPage switchToMenuItem(MenuItem menuItem) {
        $$("#hdtb-msb .hdtb-mitem").find(Condition.text(menuItem.getDesc())).click();
        return this;
    }
}
