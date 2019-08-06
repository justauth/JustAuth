package me.zhyd.oauth.utils;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class StringUtilsTest {
    @Rule
    public final ExpectedException thrown =
        ExpectedException.none();

    @Test
    public void isEmptyNonEmptyInput() {
        Assert.assertFalse(StringUtils.isEmpty("non-empty string"));
    }

    @Test
    public void isEmptyEmptyInput() {
        Assert.assertTrue(StringUtils.isEmpty(""));
    }

    @Test
    public void isEmptyInputNull() {
        Assert.assertTrue(StringUtils.isEmpty(null));
    }

    @Test
    public void isNotEmptyNonEmptyInput() {
        Assert.assertTrue(StringUtils.isNotEmpty("non-empty string"));
    }

    @Test
    public void isNotEmptyEmptyInput() {
        Assert.assertFalse(StringUtils.isNotEmpty(""));
    }

    @Test
    public void isNotEmptyInputNull() {
        Assert.assertFalse(StringUtils.isNotEmpty(null));
    }

    @Test
    public void appendIfNotContainAppendedStringNotPresent() {
        // (Check the case where appendStr doesn't occur in str)
        final String str = "Prefix ";
        final String appendStr = "suffix";
        final String otherwise = "should be discarded";

        final String result =
            StringUtils.appendIfNotContain(str, appendStr, otherwise);

        Assert.assertEquals("Prefix suffix", result);
    }

    @Test
    public void appendIfNotContainAppendedStringPresent() {
        // (Check the case where appendStr occurs in str)
        final String str = "Prefix ";
        final String appendStr = "Prefix";
        final String otherwise = "should be appended";

        final String result =
            StringUtils.appendIfNotContain(str, appendStr, otherwise);

        Assert.assertEquals("Prefix should be appended", result);
    }

    @Test
    public void appendIfNotContainEmptyString() {
        // (Check the special-case for str being empty)
        final String str = "";
        final String appendStr = "should not be appended";
        final String otherwise = "should also not be appended";

        final String result =
            StringUtils.appendIfNotContain(str, appendStr, otherwise);

        Assert.assertEquals("", result);
    }

    @Test
    public void appendIfNotContainAppendingEmptyString() {
        // (Check the special-case for appendStr being empty)
        final String str = "should be kept";
        final String appendStr = "";
        final String otherwise = "should also not be appended";

        final String result =
            StringUtils.appendIfNotContain(str, appendStr, otherwise);

        Assert.assertEquals("should be kept", result);
    }
}
