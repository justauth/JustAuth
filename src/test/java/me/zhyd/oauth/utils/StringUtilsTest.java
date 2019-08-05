package me.zhyd.oauth.utils;

import me.zhyd.oauth.utils.StringUtils;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.rules.Timeout;

public class StringUtilsTest {

    @Rule public final ExpectedException thrown =
        ExpectedException.none();

    @Rule public final Timeout globalTimeout = new Timeout(10000);

    // Test written by Diffblue Cover.
    @Test
    public void isEmptyNonEmptyInput() {

        // Act
        final boolean actual = StringUtils.isEmpty("Hello world");

        // Assert result
        Assert.assertFalse(actual);
    }

    // Test written by Diffblue Cover.
    @Test
    public void isEmptyEmptyInput() {

        // Act
        final boolean actual = StringUtils.isEmpty("");

        // Assert result
        Assert.assertTrue(actual);
    }

    // Test written by Diffblue Cover.
    @Test
    public void isEmptyInputNull() {

        // Act
        final boolean actual = StringUtils.isEmpty(null);

        // Assert result
        Assert.assertTrue(actual);
    }

    // Test written by Diffblue Cover.
    @Test
    public void isNotEmptyNonEmptyInput() {

        // Act
        final boolean actual = StringUtils.isNotEmpty("Hello world");

        // Assert result
        Assert.assertTrue(actual);
    }

    // Test written by Diffblue Cover.
    @Test
    public void isNotEmptyEmptyInput() {

        // Act
        final boolean actual = StringUtils.isNotEmpty("");

        // Assert result
        Assert.assertFalse(actual);
    }

    // Test written by Diffblue Cover.
    @Test
    public void isNotEmptyInputNull() {

        // Act
        final boolean actual = StringUtils.isNotEmpty(null);

        // Assert result
        Assert.assertFalse(actual);
    }

    /* testedClasses: StringUtils */
    // Test written by Diffblue Cover.
    @Test
    public void appendIfNotContainAppendedStringNotPresent() {

        // Arrange
        final String str = "1";
        final String appendStr = "2";
        final String otherwise = "should not be appended";

        // Act
        final String actual =
            StringUtils.appendIfNotContain(str, appendStr, otherwise);

        // Assert result
        Assert.assertEquals("12", actual);
    }

    // Test written by Diffblue Cover.
    @Test
    public void appendIfNotContainAppendedStringPresent() {

        // Arrange
        final String str = "2";
        final String appendStr = "2";
        final String otherwise = "should be appended";

        // Act
        final String actual =
            StringUtils.appendIfNotContain(str, appendStr, otherwise);

        // Assert result
        Assert.assertEquals("2should be appended", actual);
    }

    // Test written by Diffblue Cover.
    @Test
    public void appendIfNotContainEmptyString() {

        // Arrange
        final String str = "";
        final String appendStr = "should not be appended";
        final String otherwise = "should also not be appended";

        // Act
        final String actual =
            StringUtils.appendIfNotContain(str, appendStr, otherwise);

        // Assert result
        Assert.assertEquals("", actual);
    }

    // Test written by Diffblue Cover.
    @Test
    public void appendIfNotContainAppendingEmptyString() {

        // Arrange
        final String str = "should be kept";
        final String appendStr = "";
        final String otherwise = "should also not be appended";

        // Act
        final String actual =
            StringUtils.appendIfNotContain(str, appendStr, otherwise);

        // Assert result
        Assert.assertEquals("should be kept", actual);
    }

}
