package io.symbolik.pages.left;

import io.symbolik.pages.AbstractPage;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.openqa.selenium.WebDriver;

@Data
@EqualsAndHashCode(callSuper=false)
public class FavoritesPanel extends AbstractPage {

    public FavoritesPanel(final WebDriver driver) {
        super(driver);
    }

    public void searchForFavorite(String query) {

    }

    public void addFavoriteToChart(String name) {

    }

    public void removeFavorite(String name) {

    }
}
