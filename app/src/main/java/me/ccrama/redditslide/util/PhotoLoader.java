package me.ccrama.redditslide.util;

import android.content.Context;

import net.dean.jraw.models.Submission;
import net.dean.jraw.models.Thumbnails;

import java.util.List;

import me.ccrama.redditslide.ContentType;
import me.ccrama.redditslide.Reddit;
import me.ccrama.redditslide.util.preference.PreferenceHelper;

public class PhotoLoader {

    public static void loadPhoto(final Context c, final Submission submission) {
        String url;
        final ContentType.Type type = ContentType.getContentType(submission);
        final Thumbnails thumbnails = submission.getThumbnails();
        final Submission.ThumbnailType thumbnailType = submission.getThumbnailType();

        if (thumbnails != null) {

            if (type == ContentType.Type.IMAGE
                    || type == ContentType.Type.SELF
                    || thumbnailType == Submission.ThumbnailType.URL) {
                if (type == ContentType.Type.IMAGE) {
                    if ((!NetworkUtil.isConnectedWifi(c) && PreferenceHelper.isDataSavingMobile()
                            || PreferenceHelper.isDataSavingAlways())
                            && thumbnails.getVariations() != null
                            && thumbnails.getVariations().length > 0) {

                        final int length = thumbnails.getVariations().length;
                        if (PreferenceHelper.isImageQualityLow() && length >= 3) {
                            url = getThumbnailUrl(thumbnails.getVariations()[2]);
                        } else if (PreferenceHelper.isImageQualityMedium() && length >= 4) {
                            url = getThumbnailUrl(thumbnails.getVariations()[3]);
                        } else if (length >= 5) {
                            url = getThumbnailUrl(thumbnails.getVariations()[length - 1]);
                        } else {
                            url = getThumbnailUrl(thumbnails.getSource());
                        }

                    } else {
                        if (submission.getDataNode().has("preview") && submission.getDataNode()
                                .get("preview")
                                .get("images")
                                .get(0)
                                .get("source")
                                .has("height")) { //Load the preview image which has probably already been cached in memory instead of the direct link
                            url = submission.getDataNode()
                                    .get("preview")
                                    .get("images")
                                    .get(0)
                                    .get("source")
                                    .get("url")
                                    .asText();
                        } else {
                            url = submission.getUrl();
                        }
                    }

                } else {

                    if ((!NetworkUtil.isConnectedWifi(c) && PreferenceHelper.isDataSavingMobile()
                            || PreferenceHelper.isDataSavingAlways())
                            && thumbnails.getVariations().length != 0) {

                        final int length = thumbnails.getVariations().length;
                        if (PreferenceHelper.isImageQualityLow() && length >= 3) {
                            url = getThumbnailUrl(thumbnails.getVariations()[2]);
                        } else if (PreferenceHelper.isImageQualityMedium() && length >= 4) {
                            url = getThumbnailUrl(thumbnails.getVariations()[3]);
                        } else if (length >= 5) {
                            url = getThumbnailUrl(thumbnails.getVariations()[length - 1]);
                        } else {
                            url = getThumbnailUrl(thumbnails.getSource());
                        }
                    } else {
                        url = getThumbnailUrl(thumbnails.getSource());
                    }
                }
                loadImage(c, url);
            }
        }
    }

    private static String getThumbnailUrl(final Thumbnails.Image thumbnail) {
        return CompatUtil.fromHtml(thumbnail.getUrl()).toString(); //unescape url characters
    }

    private static void loadImage(final Context context, final String url) {
        final Reddit appContext = (Reddit) context.getApplicationContext();

        appContext.getImageLoader()
                .loadImage(url, null);
    }

    public static void loadPhotos(final Context c, final List<Submission> submissions) {
        for (final Submission submission : submissions) {
            loadPhoto(c, submission);
        }
    }
}
