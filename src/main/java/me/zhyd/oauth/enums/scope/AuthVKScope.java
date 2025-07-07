package me.zhyd.oauth.enums.scope;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum AuthVKScope implements AuthScope {

    /**
     * {@code scope} 含义，以{@code description} 为准
     */
    PERSONAL("vkid.personal_info", "Last name, first name, gender, profile photo and date of birth. The basic permission used by default for all apps", true),
    EMAIL("email", "Access to the user's email", true),
    PHONE("phone", "Access to the user's phone number", false),
    FRIENDS("friends", "Access to friends", false),
    WALL("wall", "Access to standard and advanced wall methods", false),
    GROUPS("groups", "Access to the user's groups", false),
    STORIES("stories", "Access to stories", false),
    DOCS("docs", "Access to documents", false),
    PHOTOS("photos", "Access to photos", false),
    ADS("ads", "Access to advanced methods of the advertising API", false),
    VIDEO("video", "Access to videos", false),
    STATUS("status", "Access to the user's status", false),
    MARKET("market", "Access to products", false),
    PAGES("pages", "Access to wiki pages", false),
    NOTIFICATIONS("notifications", "Access to notifications about responses to the user", false),
    STATS("stats", "Access to statistics of the user's groups and apps for which they are an administrator", false),
    NOTES("notes", "Access to notes", false);

    private final String scope;
    private final String description;
    private final boolean isDefault;

}
