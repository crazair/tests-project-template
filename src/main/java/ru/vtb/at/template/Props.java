package ru.vtb.at.template;

import org.aeonbits.owner.Config;

@Config.LoadPolicy(Config.LoadType.MERGE)
@Config.Sources({
        "system:properties",
        "system:env",
        "file:src/test/resources/test.properties"
})
public interface Props extends Config {

    @Key("vtb.url")
    String vtbUrl();

    @Key("profile.url")
    String profileUrl();

    @Key("selenoid.url")
    String selenoidUrl();

    @Key("db.url")
    String dbUrl();

    @Key("db.login")
    String dbLogin();

    @Key("db.password")
    String dbPassword();

    @Key("timeout")
    @DefaultValue(value = "10000")
    int timeout();
}
