package com.sonnguyen.common.model.infrastructure.support.enums;

public enum LocaleCode {
    VI("vi"), // Vietnamese
    EN("en"), // English
    FR("fr"), // French
    DE("de"), // German
    ES("es"), // Spanish
    IT("it"), // Italian
    JA("ja"), // Japanese
    KO("ko"), // Korean
    ZH("zh"), // Chinese
    RU("ru"), // Russian
    AR("ar"), // Arabic
    HI("hi"), // Hindi
    BN("bn"), // Bengali
    PT("pt"), // Portuguese
    TR("tr"), // Turkish
    TH("th"), // Thai
    NL("nl"), // Dutch
    PL("pl"), // Polish
    UK("uk"), // Ukrainian
    CS("cs"), // Czech
    FA("fa"), // Persian
    RO("ro"), // Romanian
    HU("hu"), // Hungarian
    EL("el"), // Greek
    SV("sv"), // Swedish
    ID("id"), // Indonesian
    MS("ms"), // Malay
    DA("da"), // Danish
    FI("fi"), // Finnish
    SK("sk"), // Slovak
    NO("no"), // Norwegian
    HE("he"), // Hebrew
    LT("lt"), // Lithuanian
    BG("bg"), // Bulgarian
    HR("hr"), // Croatian
    SL("sl"), // Slovenian
    ET("et"), // Estonian
    LV("lv"), // Latvian
    SR("sr"), // Serbian
    IS("is"), // Icelandic
    MK("mk"), // Macedonian
    AF("af"), // Afrikaans
    KA("ka"), // Georgian
    EU("eu"), // Basque
    CA("ca"), // Catalan
    GL("gl"), // Galician
    KM("km"), // Khmer
    LO("lo"), // Lao
    MY("my"), // Burmese
    NE("ne"), // Nepali
    SI("si"), // Sinhala
    SQ("sq"), // Albanian
    UR("ur"), // Urdu
    BE("be"), // Belarusian
    KK("kk"), // Kazakh
    MN("mn"), // Mongolian
    UZ("uz")  // Uzbek
    ;
    public String code;

    LocaleCode(String code) {
        this.code = code;
    }
}
