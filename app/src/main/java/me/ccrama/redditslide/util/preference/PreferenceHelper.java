package me.ccrama.redditslide.util.preference;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Resources;

import androidx.annotation.StringRes;
import androidx.preference.PreferenceManager;

/**
 * Created by TacoTheDank on 05/12/2021.
 * <p>
 * When adding new settings, please adhere to the preference organization!
 */
public class PreferenceHelper {

    private static SharedPreferences sharedPrefs;
    private static Resources mRes;

    public static void init(final Context context, final Resources res) {
        sharedPrefs = PreferenceManager.getDefaultSharedPreferences(context);
        mRes = res;
    }

    private static String getPrefKey(@StringRes final int prefKey) {
        return mRes.getString(prefKey);
    }

    private static boolean getHelperBoolean(@StringRes final int prefKey, final boolean defValue) {
        return sharedPrefs.getBoolean(getPrefKey(prefKey), defValue);
    }

    private static String getHelperString(@StringRes final int prefKey, final int defValue) {
        return sharedPrefs.getString(getPrefKey(prefKey), getPrefKey(defValue));
    }

    /**
     * Compares the preference's current value to the called prefKey.
     *
     * @param currentKeyValue the current key string value of a prefKey
     * @param prefKey         a passed prefKey
     * @return true if the prefKey's current value is equal to the passed prefKey, false otherwise
     */
    private static boolean getEquals(final String currentKeyValue, @StringRes final int prefKey) {
        return currentKeyValue.equals(getPrefKey(prefKey));
    }

    //////////////////////////////////////////////////
    // Multi-column settings
    //////////////////////////////////////////////////
    public static boolean singleColumnMultiWindow() {
        return getHelperBoolean(PrefKeys.PREF_SINGLE_COLUMN_MULTI_WINDOW, false);
    }

    public static boolean portraitModeDualColumns() {
        return getHelperBoolean(PrefKeys.PREF_PORTRAIT_MODE_DUAL_COLUMNS, false);
    }

    public static boolean forceFullCommentView() {
        return getHelperBoolean(PrefKeys.PREF_FORCE_FULL_COMMENT_VIEW, false);
    }


    //////////////////////////////////////////////////
    // Comments settings
    //////////////////////////////////////////////////
    public static boolean cropImage() {
        return getHelperBoolean(PrefKeys.PREF_CROP_IMAGE, true);
    }

    public static boolean colorCommentDepth() {
        return getHelperBoolean(PrefKeys.PREF_COLOR_COMMENT_DEPTH, true);
    }

    public static boolean highlightCommentOP() {
        return getHelperBoolean(PrefKeys.PREF_HIGHLIGHT_COMMENT_OP, true);
    }

    public static boolean wideDepth() {
        return getHelperBoolean(PrefKeys.PREF_WIDE_DEPTH, false);
    }

    public static boolean showCommentFab() {
        return getHelperBoolean(PrefKeys.PREF_SHOW_COMMENT_FAB, false);
    }

    public static boolean rightHandedCommentMenu() {
        return getHelperBoolean(PrefKeys.PREF_RIGHT_HANDED_COMMENT_MENU, false);
    }

    public static boolean showUpvotePercentage() {
        return getHelperBoolean(PrefKeys.PREF_SHOW_UPVOTE_PERCENTAGE, false);
    }

    public static boolean coloredTimeBubble() {
        return getHelperBoolean(PrefKeys.PREF_COLORED_TIME_BUBBLE, true);
    }

    public static boolean hideCommentAwards() {
        return getHelperBoolean(PrefKeys.PREF_HIDE_COMMENT_AWARDS, false);
    }

    public static boolean parentCommentNav() {
        return getHelperBoolean(PrefKeys.PREF_PARENT_COMMENT_NAV, true);
    }

    public static boolean autohideCommentNavBar() {
        return getHelperBoolean(PrefKeys.PREF_AUTOHIDE_COMMENT_NAVBAR, false);
    }

    public static boolean showCollapseExpandButton() {
        return getHelperBoolean(PrefKeys.PREF_SHOW_COLLAPSE_EXPAND_BUTTON, false);
    }

    public static boolean volumeNavComments() {
        return getHelperBoolean(PrefKeys.PREF_VOLUME_NAV_COMMENTS, false);
    }

    public static boolean navbarVoteGestures() {
        return getHelperBoolean(PrefKeys.PREF_NAVBAR_VOTE_GESTURES, false);
    }

    public static boolean swapLongpressTap() {
        return getHelperBoolean(PrefKeys.PREF_SWAP_LONGPRESS_TAP, false);
    }

    public static boolean fullyCollapseComments() {
        return getHelperBoolean(PrefKeys.PREF_FULLY_COLLAPSE_COMMENTS, false);
    }

    public static boolean collapseChildComments() {
        return getHelperBoolean(PrefKeys.PREF_COLLAPSE_CHILD_COMMENTS, false);
    }

    public static boolean collapseDeletedComments() {
        return getHelperBoolean(PrefKeys.PREF_COLLAPSE_DELETED_COMMENTS, false);
    }


    //////////////////////////////////////////////////
    // History settings
    //////////////////////////////////////////////////
    public static boolean storeHistory() {
        return getHelperBoolean(PrefKeys.PREF_STORE_HISTORY, true);
    }

    public static boolean storeNsfwHistory() {
        return getHelperBoolean(PrefKeys.PREF_STORE_NSFW_HISTORY, false);
    }

    public static boolean scrollSeen() {
        return getHelperBoolean(PrefKeys.PREF_SCROLL_SEEN, false);
    }


    //////////////////////////////////////////////////
    // Data-saving settings
    //////////////////////////////////////////////////
    private static String getDataSavingEnabledState() {
        return getHelperString(PrefKeys.PREF_ENABLE_DATA_SAVING, PrefKeys.PREF_ENABLE_DATA_SAVING_NEVER);
    }

    public static boolean isDataSavingNever() {
        return getEquals(getDataSavingEnabledState(), PrefKeys.PREF_ENABLE_DATA_SAVING_NEVER);
    }

    public static boolean isDataSavingMobile() {
        return getEquals(getDataSavingEnabledState(), PrefKeys.PREF_ENABLE_DATA_SAVING_MOBILE);
    }

    public static boolean isDataSavingAlways() {
        return getEquals(getDataSavingEnabledState(), PrefKeys.PREF_ENABLE_DATA_SAVING_ALWAYS);
    }

    private static String getImageQualityState() {
        return getHelperString(PrefKeys.PREF_IMAGE_QUALITY, PrefKeys.PREF_IMAGE_QUALITY_MEDIUM);
    }

    public static boolean isImageQualityNeverLoad() {
        return getEquals(getImageQualityState(), PrefKeys.PREF_IMAGE_QUALITY_NEVERLOAD);
    }

    // Returns whether at least any image loading is enabled, regardless of quality
    public static boolean imageLoadingEnabled() {//"imageLq"
        return !isImageQualityNeverLoad();
    }

    public static boolean isImageQualityLow() {
        return getEquals(getImageQualityState(), PrefKeys.PREF_IMAGE_QUALITY_LOW);
    }

    public static boolean isImageQualityMedium() {
        return getEquals(getImageQualityState(), PrefKeys.PREF_IMAGE_QUALITY_MEDIUM);
    }

    public static boolean isImageQualityHigh() {
        return getEquals(getImageQualityState(), PrefKeys.PREF_IMAGE_QUALITY_HIGH);
    }

    public static boolean preferLowQualityVideos() {
        return getHelperBoolean(PrefKeys.PREF_LOW_QUALITY_VIDEOS, true);
    }
}
