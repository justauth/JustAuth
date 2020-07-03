package me.zhyd.oauth.utils;

import org.junit.Test;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author yadong.zhang (yadong.zhang0415(a)gmail.com)
 * @version 1.0.0
 * @date 2020/7/3 16:06
 * @since 1.0.0
 */
public class ScopeTest {

    @Test
    public void googleScope() {
        String scopeStr = "cloud-platform(\"https://www.googleapis.com/auth/cloud-platform\", \"View and manage your data across Google Cloud Platform services\"),\n" +
            "cloud-platform.read-only(\"https://www.googleapis.com/auth/cloud-platform.read-only\", \"View your data across Google Cloud Platform services\"),\n" +
            "cloud-platform(\"https://www.googleapis.com/auth/cloud-platform\", \"View and manage your data across Google Cloud Platform services\"),\n" +
            "cloud-platform(\"https://www.googleapis.com/auth/cloud-platform\", \"View and manage your data across Google Cloud Platform services\"),\n" +
            "adexchange.buyer(\"https://www.googleapis.com/auth/adexchange.buyer\", \"Manage your Ad Exchange buyer account configuration\"),\n" +
            "adexchange.buyer(\"https://www.googleapis.com/auth/adexchange.buyer\", \"Manage your Ad Exchange buyer account configuration\"),\n" +
            "adsensehost(\"https://www.googleapis.com/auth/adsensehost\", \"View and manage your AdSense host data and associated accounts\"),\n" +
            "adsense(\"https://www.googleapis.com/auth/adsense\", \"View and manage your AdSense data\"),\n" +
            "adsense.readonly(\"https://www.googleapis.com/auth/adsense.readonly\", \"View your AdSense data\"),\n" +
            "admin.datatransfer(\"https://www.googleapis.com/auth/admin.datatransfer\", \"View and manage data transfers between users in your organization\"),\n" +
            "admin.datatransfer.readonly(\"https://www.googleapis.com/auth/admin.datatransfer.readonly\", \"View data transfers between users in your organization\"),\n" +
            "admin.directory.customer(\"https://www.googleapis.com/auth/admin.directory.customer\", \"View and manage customer related information\"),\n" +
            "admin.directory.customer.readonly(\"https://www.googleapis.com/auth/admin.directory.customer.readonly\", \"View customer related information\"),\n" +
            "admin.directory.device.chromeos(\"https://www.googleapis.com/auth/admin.directory.device.chromeos\", \"View and manage your Chrome OS devices' metadata\"),\n" +
            "admin.directory.device.chromeos.readonly(\"https://www.googleapis.com/auth/admin.directory.device.chromeos.readonly\", \"View your Chrome OS devices' metadata\"),\n" +
            "admin.directory.device.mobile(\"https://www.googleapis.com/auth/admin.directory.device.mobile\", \"View and manage your mobile devices' metadata\"),\n" +
            "admin.directory.device.mobile.action(\"https://www.googleapis.com/auth/admin.directory.device.mobile.action\", \"Manage your mobile devices by performing administrative tasks\"),\n" +
            "admin.directory.device.mobile.readonly(\"https://www.googleapis.com/auth/admin.directory.device.mobile.readonly\", \"View your mobile devices' metadata\"),\n" +
            "admin.directory.domain(\"https://www.googleapis.com/auth/admin.directory.domain\", \"View and manage the provisioning of domains for your customers\"),\n" +
            "admin.directory.domain.readonly(\"https://www.googleapis.com/auth/admin.directory.domain.readonly\", \"View domains related to your customers\"),\n" +
            "admin.directory.group(\"https://www.googleapis.com/auth/admin.directory.group\", \"View and manage the provisioning of groups on your domain\"),\n" +
            "admin.directory.group.member(\"https://www.googleapis.com/auth/admin.directory.group.member\", \"View and manage group subscriptions on your domain\"),\n" +
            "admin.directory.group.member.readonly(\"https://www.googleapis.com/auth/admin.directory.group.member.readonly\", \"View group subscriptions on your domain\"),\n" +
            "admin.directory.group.readonly(\"https://www.googleapis.com/auth/admin.directory.group.readonly\", \"View groups on your domain\"),\n" +
            "admin.directory.notifications(\"https://www.googleapis.com/auth/admin.directory.notifications\", \"View and manage notifications received on your domain\"),\n" +
            "admin.directory.orgunit(\"https://www.googleapis.com/auth/admin.directory.orgunit\", \"View and manage organization units on your domain\"),\n" +
            "admin.directory.orgunit.readonly(\"https://www.googleapis.com/auth/admin.directory.orgunit.readonly\", \"View organization units on your domain\"),\n" +
            "admin.directory.resource.calendar(\"https://www.googleapis.com/auth/admin.directory.resource.calendar\", \"View and manage the provisioning of calendar resources on your domain\"),\n" +
            "admin.directory.resource.calendar.readonly(\"https://www.googleapis.com/auth/admin.directory.resource.calendar.readonly\", \"View calendar resources on your domain\"),\n" +
            "admin.directory.rolemanagement(\"https://www.googleapis.com/auth/admin.directory.rolemanagement\", \"Manage delegated admin roles for your domain\"),\n" +
            "admin.directory.rolemanagement.readonly(\"https://www.googleapis.com/auth/admin.directory.rolemanagement.readonly\", \"View delegated admin roles for your domain\"),\n" +
            "admin.directory.user(\"https://www.googleapis.com/auth/admin.directory.user\", \"View and manage the provisioning of users on your domain\"),\n" +
            "admin.directory.user.alias(\"https://www.googleapis.com/auth/admin.directory.user.alias\", \"View and manage user aliases on your domain\"),\n" +
            "admin.directory.user.alias.readonly(\"https://www.googleapis.com/auth/admin.directory.user.alias.readonly\", \"View user aliases on your domain\"),\n" +
            "admin.directory.user.readonly(\"https://www.googleapis.com/auth/admin.directory.user.readonly\", \"View users on your domain\"),\n" +
            "admin.directory.user.security(\"https://www.googleapis.com/auth/admin.directory.user.security\", \"Manage data access permissions for users on your domain\"),\n" +
            "admin.directory.userschema(\"https://www.googleapis.com/auth/admin.directory.userschema\", \"View and manage the provisioning of user schemas on your domain\"),\n" +
            "admin.directory.userschema.readonly(\"https://www.googleapis.com/auth/admin.directory.userschema.readonly\", \"View user schemas on your domain\"),\n" +
            "cloud-platform(\"https://www.googleapis.com/auth/cloud-platform\", \"View and manage your data across Google Cloud Platform services\"),\n" +
            "admin.reports.audit.readonly(\"https://www.googleapis.com/auth/admin.reports.audit.readonly\", \"View audit reports for your G Suite domain\"),\n" +
            "admin.reports.usage.readonly(\"https://www.googleapis.com/auth/admin.reports.usage.readonly\", \"View usage reports for your G Suite domain\"),\n" +
            "analytics(\"https://www.googleapis.com/auth/analytics\", \"View and manage your Google Analytics data\"),\n" +
            "analytics.readonly(\"https://www.googleapis.com/auth/analytics.readonly\", \"View your Google Analytics data\"),\n" +
            "androidmanagement(\"https://www.googleapis.com/auth/androidmanagement\", \"Manage Android devices and apps for your customers\"),\n" +
            "cloud-platform(\"https://www.googleapis.com/auth/cloud-platform\", \"View and manage your data across Google Cloud Platform services\"),\n" +
            "appengine.admin(\"https://www.googleapis.com/auth/appengine.admin\", \"View and manage your applications deployed on Google App Engine\"),\n" +
            "cloud-platform(\"https://www.googleapis.com/auth/cloud-platform\", \"View and manage your data across Google Cloud Platform services\"),\n" +
            "cloud-platform.read-only(\"https://www.googleapis.com/auth/cloud-platform.read-only\", \"View your data across Google Cloud Platform services\"),\n" +
            "(\"https://mail.google.com/\", \"Read, compose, send, and permanently delete all your email from Gmail\"),\n" +
            "feeds(\"https://www.google.com/calendar/feeds\", \"See, edit, share, and permanently delete all the calendars you can access using Google Calendar\"),\n" +
            "feeds(\"https://www.google.com/m8/feeds\", \"See, edit, download, and permanently delete your contacts\"),\n" +
            "admin.directory.group(\"https://www.googleapis.com/auth/admin.directory.group\", \"View and manage the provisioning of groups on your domain\"),\n" +
            "admin.directory.user(\"https://www.googleapis.com/auth/admin.directory.user\", \"View and manage the provisioning of users on your domain\"),\n" +
            "documents(\"https://www.googleapis.com/auth/documents\", \"View and manage your Google Docs documents\"),\n" +
            "drive(\"https://www.googleapis.com/auth/drive\", \"See, edit, create, and delete all of your Google Drive files\"),\n" +
            "forms(\"https://www.googleapis.com/auth/forms\", \"View and manage your forms in Google Drive\"),\n" +
            "forms.currentonly(\"https://www.googleapis.com/auth/forms.currentonly\", \"View and manage forms that this application has been installed in\"),\n" +
            "groups(\"https://www.googleapis.com/auth/groups\", \"View and manage your Google Groups\"),\n" +
            "script.deployments(\"https://www.googleapis.com/auth/script.deployments\", \"Create and update Google Apps Script deployments\"),\n" +
            "script.deployments.readonly(\"https://www.googleapis.com/auth/script.deployments.readonly\", \"View Google Apps Script deployments\"),\n" +
            "script.metrics(\"https://www.googleapis.com/auth/script.metrics\", \"View Google Apps Script project's metrics\"),\n" +
            "script.processes(\"https://www.googleapis.com/auth/script.processes\", \"View Google Apps Script processes\"),\n" +
            "script.projects(\"https://www.googleapis.com/auth/script.projects\", \"Create and update Google Apps Script projects\"),\n" +
            "script.projects.readonly(\"https://www.googleapis.com/auth/script.projects.readonly\", \"View Google Apps Script projects\"),\n" +
            "spreadsheets(\"https://www.googleapis.com/auth/spreadsheets\", \"See, edit, create, and delete your spreadsheets in Google Drive\"),\n" +
            "userinfo.email(\"https://www.googleapis.com/auth/userinfo.email\", \"View your email address\"),\n" +
            "cloud-platform(\"https://www.googleapis.com/auth/cloud-platform\", \"View and manage your data across Google Cloud Platform services\"),\n" +
            "cloud-platform.read-only(\"https://www.googleapis.com/auth/cloud-platform.read-only\", \"View your data across Google Cloud Platform services\"),\n" +
            "bigquery(\"https://www.googleapis.com/auth/bigquery\", \"View and manage your data in Google BigQuery\"),\n" +
            "bigquery.insertdata(\"https://www.googleapis.com/auth/bigquery.insertdata\", \"Insert data into Google BigQuery\"),\n" +
            "bigquery.readonly(\"https://www.googleapis.com/auth/bigquery.readonly\", \"View your data in Google BigQuery\"),\n" +
            "cloud-platform(\"https://www.googleapis.com/auth/cloud-platform\", \"View and manage your data across Google Cloud Platform services\"),\n" +
            "cloud-platform.read-only(\"https://www.googleapis.com/auth/cloud-platform.read-only\", \"View your data across Google Cloud Platform services\"),\n" +
            "devstorage.full_control(\"https://www.googleapis.com/auth/devstorage.full_control\", \"Manage your data and permissions in Google Cloud Storage\"),\n" +
            "devstorage.read_only(\"https://www.googleapis.com/auth/devstorage.read_only\", \"View your data in Google Cloud Storage\"),\n" +
            "devstorage.read_write(\"https://www.googleapis.com/auth/devstorage.read_write\", \"Manage your data in Google Cloud Storage\"),\n" +
            "bigquery(\"https://www.googleapis.com/auth/bigquery\", \"View and manage your data in Google BigQuery\"),\n" +
            "cloud-platform(\"https://www.googleapis.com/auth/cloud-platform\", \"View and manage your data across Google Cloud Platform services\"),\n" +
            "bigquery(\"https://www.googleapis.com/auth/bigquery\", \"View and manage your data in Google BigQuery\"),\n" +
            "bigquery.readonly(\"https://www.googleapis.com/auth/bigquery.readonly\", \"View your data in Google BigQuery\"),\n" +
            "cloud-platform(\"https://www.googleapis.com/auth/cloud-platform\", \"View and manage your data across Google Cloud Platform services\"),\n" +
            "cloud-platform.read-only(\"https://www.googleapis.com/auth/cloud-platform.read-only\", \"View your data across Google Cloud Platform services\"),\n" +
            "bigquery(\"https://www.googleapis.com/auth/bigquery\", \"View and manage your data in Google BigQuery\"),\n" +
            "cloud-platform(\"https://www.googleapis.com/auth/cloud-platform\", \"View and manage your data across Google Cloud Platform services\"),\n" +
            "cloud-platform(\"https://www.googleapis.com/auth/cloud-platform\", \"View and manage your data across Google Cloud Platform services\"),\n" +
            "blogger(\"https://www.googleapis.com/auth/blogger\", \"Manage your Blogger account\"),\n" +
            "blogger.readonly(\"https://www.googleapis.com/auth/blogger.readonly\", \"View your Blogger account\"),\n" +
            "books(\"https://www.googleapis.com/auth/books\", \"Manage your books\"),\n" +
            "calendar(\"https://www.googleapis.com/auth/calendar\", \"See, edit, share, and permanently delete all the calendars you can access using Google Calendar\"),\n" +
            "calendar.events(\"https://www.googleapis.com/auth/calendar.events\", \"View and edit events on all your calendars\"),\n" +
            "calendar.events.readonly(\"https://www.googleapis.com/auth/calendar.events.readonly\", \"View events on all your calendars\"),\n" +
            "calendar.readonly(\"https://www.googleapis.com/auth/calendar.readonly\", \"View your calendars\"),\n" +
            "calendar.settings.readonly(\"https://www.googleapis.com/auth/calendar.settings.readonly\", \"View your Calendar settings\"),\n" +
            "verifiedaccess(\"https://www.googleapis.com/auth/verifiedaccess\", \"Verify your enterprise credentials\"),\n" +
            "cloud-platform(\"https://www.googleapis.com/auth/cloud-platform\", \"View and manage your data across Google Cloud Platform services\"),\n" +
            "bigtable.admin(\"https://www.googleapis.com/auth/bigtable.admin\", \"Administer your Cloud Bigtable tables and clusters\"),\n" +
            "bigtable.admin.cluster(\"https://www.googleapis.com/auth/bigtable.admin.cluster\", \"Administer your Cloud Bigtable clusters\"),\n" +
            "bigtable.admin.instance(\"https://www.googleapis.com/auth/bigtable.admin.instance\", \"Administer your Cloud Bigtable clusters\"),\n" +
            "bigtable.admin.table(\"https://www.googleapis.com/auth/bigtable.admin.table\", \"Administer your Cloud Bigtable tables\"),\n" +
            "cloud-bigtable.admin(\"https://www.googleapis.com/auth/cloud-bigtable.admin\", \"Administer your Cloud Bigtable tables and clusters\"),\n" +
            "cloud-bigtable.admin.cluster(\"https://www.googleapis.com/auth/cloud-bigtable.admin.cluster\", \"Administer your Cloud Bigtable clusters\"),\n" +
            "cloud-bigtable.admin.table(\"https://www.googleapis.com/auth/cloud-bigtable.admin.table\", \"Administer your Cloud Bigtable tables\"),\n" +
            "cloud-platform(\"https://www.googleapis.com/auth/cloud-platform\", \"View and manage your data across Google Cloud Platform services\"),\n" +
            "cloud-platform.read-only(\"https://www.googleapis.com/auth/cloud-platform.read-only\", \"View your data across Google Cloud Platform services\"),\n" +
            "cloud-platform(\"https://www.googleapis.com/auth/cloud-platform\", \"View and manage your data across Google Cloud Platform services\"),\n" +
            "cloud-platform(\"https://www.googleapis.com/auth/cloud-platform\", \"View and manage your data across Google Cloud Platform services\"),\n" +
            "cloud-platform(\"https://www.googleapis.com/auth/cloud-platform\", \"View and manage your data across Google Cloud Platform services\"),\n" +
            "cloud-platform(\"https://www.googleapis.com/auth/cloud-platform\", \"View and manage your data across Google Cloud Platform services\"),\n" +
            "cloud-platform(\"https://www.googleapis.com/auth/cloud-platform\", \"View and manage your data across Google Cloud Platform services\"),\n" +
            "cloud-platform.read-only(\"https://www.googleapis.com/auth/cloud-platform.read-only\", \"View your data across Google Cloud Platform services\"),\n" +
            "ndev.clouddns.readonly(\"https://www.googleapis.com/auth/ndev.clouddns.readonly\", \"View your DNS records hosted by Google Cloud DNS\"),\n" +
            "ndev.clouddns.readwrite(\"https://www.googleapis.com/auth/ndev.clouddns.readwrite\", \"View and manage your DNS records hosted by Google Cloud DNS\"),\n" +
            "cloud-platform(\"https://www.googleapis.com/auth/cloud-platform\", \"View and manage your data across Google Cloud Platform services\"),\n" +
            "cloud-platform(\"https://www.googleapis.com/auth/cloud-platform\", \"View and manage your data across Google Cloud Platform services\"),\n" +
            "cloud-platform(\"https://www.googleapis.com/auth/cloud-platform\", \"View and manage your data across Google Cloud Platform services\"),\n" +
            "cloud-platform(\"https://www.googleapis.com/auth/cloud-platform\", \"View and manage your data across Google Cloud Platform services\"),\n" +
            "datastore(\"https://www.googleapis.com/auth/datastore\", \"View and manage your Google Cloud Datastore data\"),\n" +
            "cloud-platform(\"https://www.googleapis.com/auth/cloud-platform\", \"View and manage your data across Google Cloud Platform services\"),\n" +
            "cloud_debugger(\"https://www.googleapis.com/auth/cloud_debugger\", \"Use Stackdriver Debugger\"),\n" +
            "cloud-platform(\"https://www.googleapis.com/auth/cloud-platform\", \"View and manage your data across Google Cloud Platform services\"),\n" +
            "cloud-platform(\"https://www.googleapis.com/auth/cloud-platform\", \"View and manage your data across Google Cloud Platform services\"),\n" +
            "cloud-platform(\"https://www.googleapis.com/auth/cloud-platform\", \"View and manage your data across Google Cloud Platform services\"),\n" +
            "datastore(\"https://www.googleapis.com/auth/datastore\", \"View and manage your Google Cloud Datastore data\"),\n" +
            "cloud-platform(\"https://www.googleapis.com/auth/cloud-platform\", \"View and manage your data across Google Cloud Platform services\"),\n" +
            "cloud-platform(\"https://www.googleapis.com/auth/cloud-platform\", \"View and manage your data across Google Cloud Platform services\"),\n" +
            "cloud-identity.groups(\"https://www.googleapis.com/auth/cloud-identity.groups\", \"See, change, create, and delete any of the Cloud Identity Groups that you can access, including the members of each group\"),\n" +
            "cloud-identity.groups.readonly(\"https://www.googleapis.com/auth/cloud-identity.groups.readonly\", \"See any Cloud Identity Groups that you can access, including group members and their emails\"),\n" +
            "cloud-platform(\"https://www.googleapis.com/auth/cloud-platform\", \"View and manage your data across Google Cloud Platform services\"),\n" +
            "cloud-platform(\"https://www.googleapis.com/auth/cloud-platform\", \"View and manage your data across Google Cloud Platform services\"),\n" +
            "cloud-platform(\"https://www.googleapis.com/auth/cloud-platform\", \"View and manage your data across Google Cloud Platform services\"),\n" +
            "cloudiot(\"https://www.googleapis.com/auth/cloudiot\", \"Register and manage devices in the Google Cloud IoT service\"),\n" +
            "cloud-platform(\"https://www.googleapis.com/auth/cloud-platform\", \"View and manage your data across Google Cloud Platform services\"),\n" +
            "cloudkms(\"https://www.googleapis.com/auth/cloudkms\", \"View and manage your keys and secrets stored in Cloud Key Management Service\"),\n" +
            "cloud-platform(\"https://www.googleapis.com/auth/cloud-platform\", \"View and manage your data across Google Cloud Platform services\"),\n" +
            "cloud-platform(\"https://www.googleapis.com/auth/cloud-platform\", \"View and manage your data across Google Cloud Platform services\"),\n" +
            "cloud-platform.read-only(\"https://www.googleapis.com/auth/cloud-platform.read-only\", \"View your data across Google Cloud Platform services\"),\n" +
            "logging.admin(\"https://www.googleapis.com/auth/logging.admin\", \"Administrate log data for your projects\"),\n" +
            "logging.read(\"https://www.googleapis.com/auth/logging.read\", \"View log data for your projects\"),\n" +
            "logging.write(\"https://www.googleapis.com/auth/logging.write\", \"Submit log data for your projects\"),\n" +
            "cloud-platform(\"https://www.googleapis.com/auth/cloud-platform\", \"View and manage your data across Google Cloud Platform services\"),\n" +
            "cloud-platform(\"https://www.googleapis.com/auth/cloud-platform\", \"View and manage your data across Google Cloud Platform services\"),\n" +
            "monitoring(\"https://www.googleapis.com/auth/monitoring\", \"View and write monitoring data for all of your Google and third-party Cloud and API projects\"),\n" +
            "monitoring.read(\"https://www.googleapis.com/auth/monitoring.read\", \"View monitoring data for all of your Google Cloud and third-party projects\"),\n" +
            "monitoring.write(\"https://www.googleapis.com/auth/monitoring.write\", \"Publish metric data to your Google Cloud projects\"),\n" +
            "cloud-language(\"https://www.googleapis.com/auth/cloud-language\", \"Apply machine learning models to reveal the structure and meaning of text\"),\n" +
            "cloud-platform(\"https://www.googleapis.com/auth/cloud-platform\", \"View and manage your data across Google Cloud Platform services\"),\n" +
            "cloud-platform(\"https://www.googleapis.com/auth/cloud-platform\", \"View and manage your data across Google Cloud Platform services\"),\n" +
            "cloud-platform(\"https://www.googleapis.com/auth/cloud-platform\", \"View and manage your data across Google Cloud Platform services\"),\n" +
            "compute(\"https://www.googleapis.com/auth/compute\", \"View and manage your Google Compute Engine resources\"),\n" +
            "cloud-platform(\"https://www.googleapis.com/auth/cloud-platform\", \"View and manage your data across Google Cloud Platform services\"),\n" +
            "pubsub(\"https://www.googleapis.com/auth/pubsub\", \"View and manage Pub/Sub topics and subscriptions\"),\n" +
            "cloud-platform(\"https://www.googleapis.com/auth/cloud-platform\", \"View and manage your data across Google Cloud Platform services\"),\n" +
            "cloud-platform.read-only(\"https://www.googleapis.com/auth/cloud-platform.read-only\", \"View your data across Google Cloud Platform services\"),\n" +
            "cloud-platform(\"https://www.googleapis.com/auth/cloud-platform\", \"View and manage your data across Google Cloud Platform services\"),\n" +
            "cloud-platform(\"https://www.googleapis.com/auth/cloud-platform\", \"View and manage your data across Google Cloud Platform services\"),\n" +
            "cloudruntimeconfig(\"https://www.googleapis.com/auth/cloudruntimeconfig\", \"Manage your Google Cloud Platform services' runtime configuration\"),\n" +
            "cloud-platform(\"https://www.googleapis.com/auth/cloud-platform\", \"View and manage your data across Google Cloud Platform services\"),\n" +
            "sqlservice.admin(\"https://www.googleapis.com/auth/sqlservice.admin\", \"Manage your Google SQL Service instances\"),\n" +
            "cloud-platform(\"https://www.googleapis.com/auth/cloud-platform\", \"View and manage your data across Google Cloud Platform services\"),\n" +
            "cloud_search(\"https://www.googleapis.com/auth/cloud_search\", \"Index and serve your organization's data with Cloud Search\"),\n" +
            "cloud_search.debug(\"https://www.googleapis.com/auth/cloud_search.debug\", \"Index and serve your organization's data with Cloud Search\"),\n" +
            "cloud_search.indexing(\"https://www.googleapis.com/auth/cloud_search.indexing\", \"Index and serve your organization's data with Cloud Search\"),\n" +
            "cloud_search.query(\"https://www.googleapis.com/auth/cloud_search.query\", \"Search your organization's data in the Cloud Search index\"),\n" +
            "cloud_search.settings(\"https://www.googleapis.com/auth/cloud_search.settings\", \"Index and serve your organization's data with Cloud Search\"),\n" +
            "cloud_search.settings.indexing(\"https://www.googleapis.com/auth/cloud_search.settings.indexing\", \"Index and serve your organization's data with Cloud Search\"),\n" +
            "cloud_search.settings.query(\"https://www.googleapis.com/auth/cloud_search.settings.query\", \"Index and serve your organization's data with Cloud Search\"),\n" +
            "cloud_search.stats(\"https://www.googleapis.com/auth/cloud_search.stats\", \"Index and serve your organization's data with Cloud Search\"),\n" +
            "cloud_search.stats.indexing(\"https://www.googleapis.com/auth/cloud_search.stats.indexing\", \"Index and serve your organization's data with Cloud Search\"),\n" +
            "cloud-platform(\"https://www.googleapis.com/auth/cloud-platform\", \"View and manage your data across Google Cloud Platform services\"),\n" +
            "cloud-platform(\"https://www.googleapis.com/auth/cloud-platform\", \"View and manage your data across Google Cloud Platform services\"),\n" +
            "source.full_control(\"https://www.googleapis.com/auth/source.full_control\", \"Manage your source code repositories\"),\n" +
            "source.read_only(\"https://www.googleapis.com/auth/source.read_only\", \"View the contents of your source code repositories\"),\n" +
            "source.read_write(\"https://www.googleapis.com/auth/source.read_write\", \"Manage the contents of your source code repositories\"),\n" +
            "cloud-platform(\"https://www.googleapis.com/auth/cloud-platform\", \"View and manage your data across Google Cloud Platform services\"),\n" +
            "spanner.admin(\"https://www.googleapis.com/auth/spanner.admin\", \"Administer your Spanner databases\"),\n" +
            "spanner.data(\"https://www.googleapis.com/auth/spanner.data\", \"View and manage the contents of your Spanner databases\"),\n" +
            "cloud-platform(\"https://www.googleapis.com/auth/cloud-platform\", \"View and manage your data across Google Cloud Platform services\"),\n" +
            "cloud-platform(\"https://www.googleapis.com/auth/cloud-platform\", \"View and manage your data across Google Cloud Platform services\"),\n" +
            "cloud-platform.read-only(\"https://www.googleapis.com/auth/cloud-platform.read-only\", \"View your data across Google Cloud Platform services\"),\n" +
            "devstorage.full_control(\"https://www.googleapis.com/auth/devstorage.full_control\", \"Manage your data and permissions in Google Cloud Storage\"),\n" +
            "devstorage.read_only(\"https://www.googleapis.com/auth/devstorage.read_only\", \"View your data in Google Cloud Storage\"),\n" +
            "devstorage.read_write(\"https://www.googleapis.com/auth/devstorage.read_write\", \"Manage your data in Google Cloud Storage\"),\n" +
            "cloud-platform(\"https://www.googleapis.com/auth/cloud-platform\", \"View and manage your data across Google Cloud Platform services\"),\n" +
            "cloud-platform(\"https://www.googleapis.com/auth/cloud-platform\", \"View and manage your data across Google Cloud Platform services\"),\n" +
            "jobs(\"https://www.googleapis.com/auth/jobs\", \"Manage job postings\"),\n" +
            "cloud-platform(\"https://www.googleapis.com/auth/cloud-platform\", \"View and manage your data across Google Cloud Platform services\"),\n" +
            "cloud-platform(\"https://www.googleapis.com/auth/cloud-platform\", \"View and manage your data across Google Cloud Platform services\"),\n" +
            "cloud-platform.read-only(\"https://www.googleapis.com/auth/cloud-platform.read-only\", \"View your data across Google Cloud Platform services\"),\n" +
            "cloud-platform(\"https://www.googleapis.com/auth/cloud-platform\", \"View and manage your data across Google Cloud Platform services\"),\n" +
            "cloud-platform(\"https://www.googleapis.com/auth/cloud-platform\", \"View and manage your data across Google Cloud Platform services\"),\n" +
            "cloud-platform(\"https://www.googleapis.com/auth/cloud-platform\", \"View and manage your data across Google Cloud Platform services\"),\n" +
            "trace.append(\"https://www.googleapis.com/auth/trace.append\", \"Write Trace data for a project or application\"),\n" +
            "cloud-platform(\"https://www.googleapis.com/auth/cloud-platform\", \"View and manage your data across Google Cloud Platform services\"),\n" +
            "cloud-translation(\"https://www.googleapis.com/auth/cloud-translation\", \"Translate text from one language to another using Google Translate\"),\n" +
            "cloud-platform(\"https://www.googleapis.com/auth/cloud-platform\", \"View and manage your data across Google Cloud Platform services\"),\n" +
            "cloud-platform(\"https://www.googleapis.com/auth/cloud-platform\", \"View and manage your data across Google Cloud Platform services\"),\n" +
            "cloud-vision(\"https://www.googleapis.com/auth/cloud-vision\", \"Apply machine learning models to understand and label images\"),\n" +
            "cloud-platform(\"https://www.googleapis.com/auth/cloud-platform\", \"View and manage your data across Google Cloud Platform services\"),\n" +
            "compute(\"https://www.googleapis.com/auth/compute\", \"View and manage your Google Compute Engine resources\"),\n" +
            "compute.readonly(\"https://www.googleapis.com/auth/compute.readonly\", \"View your Google Compute Engine resources\"),\n" +
            "devstorage.full_control(\"https://www.googleapis.com/auth/devstorage.full_control\", \"Manage your data and permissions in Google Cloud Storage\"),\n" +
            "devstorage.read_only(\"https://www.googleapis.com/auth/devstorage.read_only\", \"View your data in Google Cloud Storage\"),\n" +
            "devstorage.read_write(\"https://www.googleapis.com/auth/devstorage.read_write\", \"Manage your data in Google Cloud Storage\"),\n" +
            "cloud-platform(\"https://www.googleapis.com/auth/cloud-platform\", \"View and manage your data across Google Cloud Platform services\"),\n" +
            "content(\"https://www.googleapis.com/auth/content\", \"Manage your product listings and accounts for Google Shopping\"),\n" +
            "ddmconversions(\"https://www.googleapis.com/auth/ddmconversions\", \"Manage DoubleClick Digital Marketing conversions\"),\n" +
            "dfareporting(\"https://www.googleapis.com/auth/dfareporting\", \"View and manage DoubleClick for Advertisers reports\"),\n" +
            "dfatrafficking(\"https://www.googleapis.com/auth/dfatrafficking\", \"View and manage your DoubleClick Campaign Manager's (DCM) display ad campaigns\"),\n" +
            "cloud-platform(\"https://www.googleapis.com/auth/cloud-platform\", \"View and manage your data across Google Cloud Platform services\"),\n" +
            "compute(\"https://www.googleapis.com/auth/compute\", \"View and manage your Google Compute Engine resources\"),\n" +
            "compute.readonly(\"https://www.googleapis.com/auth/compute.readonly\", \"View your Google Compute Engine resources\"),\n" +
            "userinfo.email(\"https://www.googleapis.com/auth/userinfo.email\", \"View your email address\"),\n" +
            "cloud-platform(\"https://www.googleapis.com/auth/cloud-platform\", \"View and manage your data across Google Cloud Platform services\"),\n" +
            "dialogflow(\"https://www.googleapis.com/auth/dialogflow\", \"View, manage and query your Dialogflow agents\"),\n" +
            "display-video(\"https://www.googleapis.com/auth/display-video\", \"Create, see, edit, and permanently delete your Display & Video 360 entities and reports\"),\n" +
            "doubleclickbidmanager(\"https://www.googleapis.com/auth/doubleclickbidmanager\", \"View and manage your reports in DoubleClick Bid Manager\"),\n" +
            "doubleclickbidmanager(\"https://www.googleapis.com/auth/doubleclickbidmanager\", \"View and manage your reports in DoubleClick Bid Manager\"),\n" +
            "drive(\"https://www.googleapis.com/auth/drive\", \"See, edit, create, and delete all of your Google Drive files\"),\n" +
            "drive.appdata(\"https://www.googleapis.com/auth/drive.appdata\", \"View and manage its own configuration data in your Google Drive\"),\n" +
            "drive.file(\"https://www.googleapis.com/auth/drive.file\", \"View and manage Google Drive files and folders that you have opened or created with this app\"),\n" +
            "drive.metadata(\"https://www.googleapis.com/auth/drive.metadata\", \"View and manage metadata of files in your Google Drive\"),\n" +
            "drive.metadata.readonly(\"https://www.googleapis.com/auth/drive.metadata.readonly\", \"View metadata for files in your Google Drive\"),\n" +
            "drive.photos.readonly(\"https://www.googleapis.com/auth/drive.photos.readonly\", \"View the photos, videos and albums in your Google Photos\"),\n" +
            "drive.readonly(\"https://www.googleapis.com/auth/drive.readonly\", \"See and download all your Google Drive files\"),\n" +
            "drive.scripts(\"https://www.googleapis.com/auth/drive.scripts\", \"Modify your Google Apps Script scripts' behavior\"),\n" +
            "activity(\"https://www.googleapis.com/auth/activity\", \"View the activity history of your Google apps\"),\n" +
            "drive.activity(\"https://www.googleapis.com/auth/drive.activity\", \"View and add to the activity record of files in your Google Drive\"),\n" +
            "drive.activity.readonly(\"https://www.googleapis.com/auth/drive.activity.readonly\", \"View the activity record of files in your Google Drive\"),\n" +
            "apps.order(\"https://www.googleapis.com/auth/apps.order\", \"Manage users on your domain\"),\n" +
            "apps.order.readonly(\"https://www.googleapis.com/auth/apps.order.readonly\", \"Manage users on your domain\"),\n" +
            "cloud-platform(\"https://www.googleapis.com/auth/cloud-platform\", \"View and manage your data across Google Cloud Platform services\"),\n" +
            "userinfo.email(\"https://www.googleapis.com/auth/userinfo.email\", \"View your email address\"),\n" +
            "cloud-platform(\"https://www.googleapis.com/auth/cloud-platform\", \"View and manage your data across Google Cloud Platform services\"),\n" +
            "firebase(\"https://www.googleapis.com/auth/firebase\", \"View and administer all your Firebase data and settings\"),\n" +
            "cloud-platform(\"https://www.googleapis.com/auth/cloud-platform\", \"View and manage your data across Google Cloud Platform services\"),\n" +
            "cloud-platform.read-only(\"https://www.googleapis.com/auth/cloud-platform.read-only\", \"View your data across Google Cloud Platform services\"),\n" +
            "firebase(\"https://www.googleapis.com/auth/firebase\", \"View and administer all your Firebase data and settings\"),\n" +
            "firebase.readonly(\"https://www.googleapis.com/auth/firebase.readonly\", \"View all your Firebase data and settings\"),\n" +
            "cloud-platform(\"https://www.googleapis.com/auth/cloud-platform\", \"View and manage your data across Google Cloud Platform services\"),\n" +
            "cloud-platform.read-only(\"https://www.googleapis.com/auth/cloud-platform.read-only\", \"View your data across Google Cloud Platform services\"),\n" +
            "firebase(\"https://www.googleapis.com/auth/firebase\", \"View and administer all your Firebase data and settings\"),\n" +
            "firebase.readonly(\"https://www.googleapis.com/auth/firebase.readonly\", \"View all your Firebase data and settings\"),\n" +
            "cloud-platform(\"https://www.googleapis.com/auth/cloud-platform\", \"View and manage your data across Google Cloud Platform services\"),\n" +
            "firebase(\"https://www.googleapis.com/auth/firebase\", \"View and administer all your Firebase data and settings\"),\n" +
            "firebase.readonly(\"https://www.googleapis.com/auth/firebase.readonly\", \"View all your Firebase data and settings\"),\n" +
            "fitness.activity.read(\"https://www.googleapis.com/auth/fitness.activity.read\", \"Use Google Fit to see and store your physical activity data\"),\n" +
            "fitness.activity.write(\"https://www.googleapis.com/auth/fitness.activity.write\", \"See and add to your Google Fit physical activity data\"),\n" +
            "fitness.blood_glucose.read(\"https://www.googleapis.com/auth/fitness.blood_glucose.read\", \"See info about your blood glucose in Google Fit. I consent to Google sharing my blood glucose information with this app.\"),\n" +
            "fitness.blood_glucose.write(\"https://www.googleapis.com/auth/fitness.blood_glucose.write\", \"See and add info about your blood glucose to Google Fit. I consent to Google sharing my blood glucose information with this app.\"),\n" +
            "fitness.blood_pressure.read(\"https://www.googleapis.com/auth/fitness.blood_pressure.read\", \"See info about your blood pressure in Google Fit. I consent to Google sharing my blood pressure information with this app.\"),\n" +
            "fitness.blood_pressure.write(\"https://www.googleapis.com/auth/fitness.blood_pressure.write\", \"See and add info about your blood pressure in Google Fit. I consent to Google sharing my blood pressure information with this app.\"),\n" +
            "fitness.body.read(\"https://www.googleapis.com/auth/fitness.body.read\", \"See info about your body measurements and heart rate in Google Fit\"),\n" +
            "fitness.body.write(\"https://www.googleapis.com/auth/fitness.body.write\", \"See and add info about your body measurements and heart rate to Google Fit\"),\n" +
            "fitness.body_temperature.read(\"https://www.googleapis.com/auth/fitness.body_temperature.read\", \"See info about your body temperature in Google Fit. I consent to Google sharing my body temperature information with this app.\"),\n" +
            "fitness.body_temperature.write(\"https://www.googleapis.com/auth/fitness.body_temperature.write\", \"See and add to info about your body temperature in Google Fit. I consent to Google sharing my body temperature information with this app.\"),\n" +
            "fitness.location.read(\"https://www.googleapis.com/auth/fitness.location.read\", \"See your Google Fit speed and distance data\"),\n" +
            "fitness.location.write(\"https://www.googleapis.com/auth/fitness.location.write\", \"See and add to your Google Fit location data\"),\n" +
            "fitness.nutrition.read(\"https://www.googleapis.com/auth/fitness.nutrition.read\", \"See info about your nutrition in Google Fit\"),\n" +
            "fitness.nutrition.write(\"https://www.googleapis.com/auth/fitness.nutrition.write\", \"See and add to info about your nutrition in Google Fit\"),\n" +
            "fitness.oxygen_saturation.read(\"https://www.googleapis.com/auth/fitness.oxygen_saturation.read\", \"See info about your oxygen saturation in Google Fit. I consent to Google sharing my oxygen saturation information with this app.\"),\n" +
            "fitness.oxygen_saturation.write(\"https://www.googleapis.com/auth/fitness.oxygen_saturation.write\", \"See and add info about your oxygen saturation in Google Fit. I consent to Google sharing my oxygen saturation information with this app.\"),\n" +
            "fitness.reproductive_health.read(\"https://www.googleapis.com/auth/fitness.reproductive_health.read\", \"See info about your reproductive health in Google Fit. I consent to Google sharing my reporductive health information with this app.\"),\n" +
            "fitness.reproductive_health.write(\"https://www.googleapis.com/auth/fitness.reproductive_health.write\", \"See and add info about your reproductive health in Google Fit. I consent to Google sharing my reporductive health information with this app.\"),\n" +
            "apps.alerts(\"https://www.googleapis.com/auth/apps.alerts\", \"See and delete your domain's G Suite alerts, and send alert feedback\"),\n" +
            "ediscovery(\"https://www.googleapis.com/auth/ediscovery\", \"Manage your eDiscovery data\"),\n" +
            "ediscovery.readonly(\"https://www.googleapis.com/auth/ediscovery.readonly\", \"View your eDiscovery data\"),\n" +
            "cloud-platform(\"https://www.googleapis.com/auth/cloud-platform\", \"View and manage your data across Google Cloud Platform services\"),\n" +
            "genomics(\"https://www.googleapis.com/auth/genomics\", \"View and manage Genomics data\"),\n" +
            "(\"https://mail.google.com/\", \"Read, compose, send, and permanently delete all your email from Gmail\"),\n" +
            "gmail.addons.current.action.compose(\"https://www.googleapis.com/auth/gmail.addons.current.action.compose\", \"Manage drafts and send emails when you interact with the add-on\"),\n" +
            "gmail.addons.current.message.action(\"https://www.googleapis.com/auth/gmail.addons.current.message.action\", \"View your email messages when you interact with the add-on\"),\n" +
            "gmail.addons.current.message.metadata(\"https://www.googleapis.com/auth/gmail.addons.current.message.metadata\", \"View your email message metadata when the add-on is running\"),\n" +
            "gmail.addons.current.message.readonly(\"https://www.googleapis.com/auth/gmail.addons.current.message.readonly\", \"View your email messages when the add-on is running\"),\n" +
            "gmail.compose(\"https://www.googleapis.com/auth/gmail.compose\", \"Manage drafts and send emails\"),\n" +
            "gmail.insert(\"https://www.googleapis.com/auth/gmail.insert\", \"Insert mail into your mailbox\"),\n" +
            "gmail.labels(\"https://www.googleapis.com/auth/gmail.labels\", \"Manage mailbox labels\"),\n" +
            "gmail.metadata(\"https://www.googleapis.com/auth/gmail.metadata\", \"View your email message metadata such as labels and headers, but not the email body\"),\n" +
            "gmail.modify(\"https://www.googleapis.com/auth/gmail.modify\", \"View and modify but not delete your email\"),\n" +
            "gmail.readonly(\"https://www.googleapis.com/auth/gmail.readonly\", \"View your email messages and settings\"),\n" +
            "gmail.send(\"https://www.googleapis.com/auth/gmail.send\", \"Send email on your behalf\"),\n" +
            "gmail.settings.basic(\"https://www.googleapis.com/auth/gmail.settings.basic\", \"Manage your basic mail settings\"),\n" +
            "gmail.settings.sharing(\"https://www.googleapis.com/auth/gmail.settings.sharing\", \"Manage your sensitive mail settings, including who can manage your mail\"),\n" +
            "analytics(\"https://www.googleapis.com/auth/analytics\", \"View and manage your Google Analytics data\"),\n" +
            "analytics.edit(\"https://www.googleapis.com/auth/analytics.edit\", \"Edit Google Analytics management entities\"),\n" +
            "analytics.manage.users(\"https://www.googleapis.com/auth/analytics.manage.users\", \"Manage Google Analytics Account users by email address\"),\n" +
            "analytics.manage.users.readonly(\"https://www.googleapis.com/auth/analytics.manage.users.readonly\", \"View Google Analytics user permissions\"),\n" +
            "analytics.provision(\"https://www.googleapis.com/auth/analytics.provision\", \"Create a new Google Analytics account along with its default property and view\"),\n" +
            "analytics.readonly(\"https://www.googleapis.com/auth/analytics.readonly\", \"View your Google Analytics data\"),\n" +
            "analytics.user.deletion(\"https://www.googleapis.com/auth/analytics.user.deletion\", \"Manage Google Analytics user deletion requests\"),\n" +
            "classroom.announcements(\"https://www.googleapis.com/auth/classroom.announcements\", \"View and manage announcements in Google Classroom\"),\n" +
            "classroom.announcements.readonly(\"https://www.googleapis.com/auth/classroom.announcements.readonly\", \"View announcements in Google Classroom\"),\n" +
            "classroom.courses(\"https://www.googleapis.com/auth/classroom.courses\", \"Manage your Google Classroom classes\"),\n" +
            "classroom.courses.readonly(\"https://www.googleapis.com/auth/classroom.courses.readonly\", \"View your Google Classroom classes\"),\n" +
            "classroom.coursework.me(\"https://www.googleapis.com/auth/classroom.coursework.me\", \"Manage your course work and view your grades in Google Classroom\"),\n" +
            "classroom.coursework.me.readonly(\"https://www.googleapis.com/auth/classroom.coursework.me.readonly\", \"View your course work and grades in Google Classroom\"),\n" +
            "classroom.coursework.students(\"https://www.googleapis.com/auth/classroom.coursework.students\", \"Manage course work and grades for students in the Google Classroom classes you teach and view the course work and grades for classes you administer\"),\n" +
            "classroom.coursework.students.readonly(\"https://www.googleapis.com/auth/classroom.coursework.students.readonly\", \"View course work and grades for students in the Google Classroom classes you teach or administer\"),\n" +
            "classroom.guardianlinks.me.readonly(\"https://www.googleapis.com/auth/classroom.guardianlinks.me.readonly\", \"View your Google Classroom guardians\"),\n" +
            "classroom.guardianlinks.students(\"https://www.googleapis.com/auth/classroom.guardianlinks.students\", \"View and manage guardians for students in your Google Classroom classes\"),\n" +
            "classroom.guardianlinks.students.readonly(\"https://www.googleapis.com/auth/classroom.guardianlinks.students.readonly\", \"View guardians for students in your Google Classroom classes\"),\n" +
            "classroom.profile.emails(\"https://www.googleapis.com/auth/classroom.profile.emails\", \"View the email addresses of people in your classes\"),\n" +
            "classroom.profile.photos(\"https://www.googleapis.com/auth/classroom.profile.photos\", \"View the profile photos of people in your classes\"),\n" +
            "classroom.push-notifications(\"https://www.googleapis.com/auth/classroom.push-notifications\", \"Receive notifications about your Google Classroom data\"),\n" +
            "classroom.rosters(\"https://www.googleapis.com/auth/classroom.rosters\", \"Manage your Google Classroom class rosters\"),\n" +
            "classroom.rosters.readonly(\"https://www.googleapis.com/auth/classroom.rosters.readonly\", \"View your Google Classroom class rosters\"),\n" +
            "classroom.student-submissions.me.readonly(\"https://www.googleapis.com/auth/classroom.student-submissions.me.readonly\", \"View your course work and grades in Google Classroom\"),\n" +
            "classroom.student-submissions.students.readonly(\"https://www.googleapis.com/auth/classroom.student-submissions.students.readonly\", \"View course work and grades for students in the Google Classroom classes you teach or administer\"),\n" +
            "classroom.topics(\"https://www.googleapis.com/auth/classroom.topics\", \"See, create, and edit topics in Google Classroom\"),\n" +
            "classroom.topics.readonly(\"https://www.googleapis.com/auth/classroom.topics.readonly\", \"View topics in Google Classroom\"),\n" +
            "cloud-platform(\"https://www.googleapis.com/auth/cloud-platform\", \"View and manage your data across Google Cloud Platform services\"),\n" +
            "cloud-platform(\"https://www.googleapis.com/auth/cloud-platform\", \"View and manage your data across Google Cloud Platform services\"),\n" +
            "cloud-platform.read-only(\"https://www.googleapis.com/auth/cloud-platform.read-only\", \"View your data across Google Cloud Platform services\"),\n" +
            "ndev.cloudman(\"https://www.googleapis.com/auth/ndev.cloudman\", \"View and manage your Google Cloud Platform management resources and deployment status information\"),\n" +
            "ndev.cloudman.readonly(\"https://www.googleapis.com/auth/ndev.cloudman.readonly\", \"View your Google Cloud Platform management resources and deployment status information\"),\n" +
            "cloud-platform(\"https://www.googleapis.com/auth/cloud-platform\", \"View and manage your data across Google Cloud Platform services\"),\n" +
            "documents(\"https://www.googleapis.com/auth/documents\", \"View and manage your Google Docs documents\"),\n" +
            "documents.readonly(\"https://www.googleapis.com/auth/documents.readonly\", \"View your Google Docs documents\"),\n" +
            "drive(\"https://www.googleapis.com/auth/drive\", \"See, edit, create, and delete all of your Google Drive files\"),\n" +
            "drive.file(\"https://www.googleapis.com/auth/drive.file\", \"View and manage Google Drive files and folders that you have opened or created with this app\"),\n" +
            "drive.readonly(\"https://www.googleapis.com/auth/drive.readonly\", \"See and download all your Google Drive files\"),\n" +
            "cloud-platform(\"https://www.googleapis.com/auth/cloud-platform\", \"View and manage your data across Google Cloud Platform services\"),\n" +
            "firebase(\"https://www.googleapis.com/auth/firebase\", \"View and administer all your Firebase data and settings\"),\n" +
            "userinfo.email(\"https://www.googleapis.com/auth/userinfo.email\", \"View your email address\"),\n" +
            "userinfo.profile(\"https://www.googleapis.com/auth/userinfo.profile\", \"See your personal info, including any personal info you've made publicly available\"),\n" +
            "openid(\"openid\", \"Associate you with your personal info on Google\"),\n" +
            "androidpublisher(\"https://www.googleapis.com/auth/androidpublisher\", \"View and manage your Google Play Developer account\"),\n" +
            "androidpublisher(\"https://www.googleapis.com/auth/androidpublisher\", \"View and manage your Google Play Developer account\"),\n" +
            "androidenterprise(\"https://www.googleapis.com/auth/androidenterprise\", \"Manage corporate Android devices\"),\n" +
            "games(\"https://www.googleapis.com/auth/games\", \"Create, edit, and delete your Google Play Games activity\"),\n" +
            "drive.appdata(\"https://www.googleapis.com/auth/drive.appdata\", \"View and manage its own configuration data in your Google Drive\"),\n" +
            "games(\"https://www.googleapis.com/auth/games\", \"Create, edit, and delete your Google Play Games activity\"),\n" +
            "androidpublisher(\"https://www.googleapis.com/auth/androidpublisher\", \"View and manage your Google Play Developer account\"),\n" +
            "drive(\"https://www.googleapis.com/auth/drive\", \"See, edit, create, and delete all of your Google Drive files\"),\n" +
            "drive.file(\"https://www.googleapis.com/auth/drive.file\", \"View and manage Google Drive files and folders that you have opened or created with this app\"),\n" +
            "drive.readonly(\"https://www.googleapis.com/auth/drive.readonly\", \"See and download all your Google Drive files\"),\n" +
            "spreadsheets(\"https://www.googleapis.com/auth/spreadsheets\", \"See, edit, create, and delete your spreadsheets in Google Drive\"),\n" +
            "spreadsheets.readonly(\"https://www.googleapis.com/auth/spreadsheets.readonly\", \"View your Google Spreadsheets\"),\n" +
            "profile(\"profile\", \"View your basic profile info\"),\n" +
            "email(\"email\", \"View your email address\"),\n" +
            "openid(\"openid\", \"Authenticate using OpenID Connect\"),\n" +
            "siteverification(\"https://www.googleapis.com/auth/siteverification\", \"Manage the list of sites and domains you control\"),\n" +
            "siteverification.verify_only(\"https://www.googleapis.com/auth/siteverification.verify_only\", \"Manage your new site verifications with Google\"),\n" +
            "drive(\"https://www.googleapis.com/auth/drive\", \"See, edit, create, and delete all of your Google Drive files\"),\n" +
            "drive.file(\"https://www.googleapis.com/auth/drive.file\", \"View and manage Google Drive files and folders that you have opened or created with this app\"),\n" +
            "drive.readonly(\"https://www.googleapis.com/auth/drive.readonly\", \"See and download all your Google Drive files\"),\n" +
            "presentations(\"https://www.googleapis.com/auth/presentations\", \"View and manage your Google Slides presentations\"),\n" +
            "presentations.readonly(\"https://www.googleapis.com/auth/presentations.readonly\", \"View your Google Slides presentations\"),\n" +
            "spreadsheets(\"https://www.googleapis.com/auth/spreadsheets\", \"See, edit, create, and delete your spreadsheets in Google Drive\"),\n" +
            "spreadsheets.readonly(\"https://www.googleapis.com/auth/spreadsheets.readonly\", \"View your Google Spreadsheets\"),\n" +
            "apps.groups.migration(\"https://www.googleapis.com/auth/apps.groups.migration\", \"Manage messages in groups on your domain\"),\n" +
            "apps.groups.settings(\"https://www.googleapis.com/auth/apps.groups.settings\", \"View and manage the settings of a G Suite group\"),\n" +
            "cloud-platform(\"https://www.googleapis.com/auth/cloud-platform\", \"View and manage your data across Google Cloud Platform services\"),\n" +
            "cloud-platform(\"https://www.googleapis.com/auth/cloud-platform\", \"View and manage your data across Google Cloud Platform services\"),\n" +
            "indexing(\"https://www.googleapis.com/auth/indexing\", \"Submit data to Google for indexing\"),\n" +
            "cloud-platform(\"https://www.googleapis.com/auth/cloud-platform\", \"View and manage your data across Google Cloud Platform services\"),\n" +
            "cloud-platform(\"https://www.googleapis.com/auth/cloud-platform\", \"View and manage your data across Google Cloud Platform services\"),\n" +
            "apps.licensing(\"https://www.googleapis.com/auth/apps.licensing\", \"View and manage G Suite licenses for your domain\"),\n" +
            "cloud-platform(\"https://www.googleapis.com/auth/cloud-platform\", \"View and manage your data across Google Cloud Platform services\"),\n" +
            "manufacturercenter(\"https://www.googleapis.com/auth/manufacturercenter\", \"Manage your product listings for Google Manufacturer Center\"),\n" +
            "cloud-platform(\"https://www.googleapis.com/auth/cloud-platform\", \"View and manage your data across Google Cloud Platform services\"),\n" +
            "openid(\"openid\", \"Authenticate using OpenID Connect\"),\n" +
            "profile(\"profile\", \"View your basic profile info\"),\n" +
            "email(\"email\", \"View your email address\"),\n" +
            "openid(\"openid\", \"Associate you with your personal info on Google\"),\n" +
            "contacts(\"https://www.googleapis.com/auth/contacts\", \"See, edit, download, and permanently delete your contacts\"),\n" +
            "contacts.other.readonly(\"https://www.googleapis.com/auth/contacts.other.readonly\", \"See and download contact info automatically saved in your \"Other contacts\"\"),\n" +
            "contacts.readonly(\"https://www.googleapis.com/auth/contacts.readonly\", \"See and download your contacts\"),\n" +
            "directory.readonly(\"https://www.googleapis.com/auth/directory.readonly\", \"See and download your organization's GSuite directory\"),\n" +
            "user.addresses.read(\"https://www.googleapis.com/auth/user.addresses.read\", \"View your street addresses\"),\n" +
            "user.birthday.read(\"https://www.googleapis.com/auth/user.birthday.read\", \"View your complete date of birth\"),\n" +
            "user.emails.read(\"https://www.googleapis.com/auth/user.emails.read\", \"View your email addresses\"),\n" +
            "user.gender.read(\"https://www.googleapis.com/auth/user.gender.read\", \"See your gender\"),\n" +
            "user.organization.read(\"https://www.googleapis.com/auth/user.organization.read\", \"See your education, work history and org info\"),\n" +
            "user.phonenumbers.read(\"https://www.googleapis.com/auth/user.phonenumbers.read\", \"View your phone numbers\"),\n" +
            "userinfo.email(\"https://www.googleapis.com/auth/userinfo.email\", \"View your email address\"),\n" +
            "userinfo.profile(\"https://www.googleapis.com/auth/userinfo.profile\", \"See your personal info, including any personal info you've made publicly available\"),\n" +
            "photoslibrary(\"https://www.googleapis.com/auth/photoslibrary\", \"View and manage your Google Photos library\"),\n" +
            "photoslibrary.appendonly(\"https://www.googleapis.com/auth/photoslibrary.appendonly\", \"Add to your Google Photos library\"),\n" +
            "photoslibrary.readonly(\"https://www.googleapis.com/auth/photoslibrary.readonly\", \"View your Google Photos library\"),\n" +
            "photoslibrary.readonly.appcreateddata(\"https://www.googleapis.com/auth/photoslibrary.readonly.appcreateddata\", \"Manage photos added by this app\"),\n" +
            "photoslibrary.sharing(\"https://www.googleapis.com/auth/photoslibrary.sharing\", \"Manage and add to shared albums on your behalf\"),\n" +
            "cloud-platform(\"https://www.googleapis.com/auth/cloud-platform\", \"View and manage your data across Google Cloud Platform services\"),\n" +
            "cloud-platform(\"https://www.googleapis.com/auth/cloud-platform\", \"View and manage your data across Google Cloud Platform services\"),\n" +
            "cloud-platform(\"https://www.googleapis.com/auth/cloud-platform\", \"View and manage your data across Google Cloud Platform services\"),\n" +
            "userinfo.email(\"https://www.googleapis.com/auth/userinfo.email\", \"View your email address\"),\n" +
            "userinfo.email(\"https://www.googleapis.com/auth/userinfo.email\", \"View your email address\"),\n" +
            "doubleclicksearch(\"https://www.googleapis.com/auth/doubleclicksearch\", \"View and manage your advertising data in DoubleClick Search\"),\n" +
            "webmasters(\"https://www.googleapis.com/auth/webmasters\", \"View and manage Search Console data for your verified sites\"),\n" +
            "webmasters.readonly(\"https://www.googleapis.com/auth/webmasters.readonly\", \"View Search Console data for your verified sites\"),\n" +
            "cloud-platform(\"https://www.googleapis.com/auth/cloud-platform\", \"View and manage your data across Google Cloud Platform services\"),\n" +
            "cloud-platform(\"https://www.googleapis.com/auth/cloud-platform\", \"View and manage your data across Google Cloud Platform services\"),\n" +
            "cloud-platform(\"https://www.googleapis.com/auth/cloud-platform\", \"View and manage your data across Google Cloud Platform services\"),\n" +
            "cloud-platform(\"https://www.googleapis.com/auth/cloud-platform\", \"View and manage your data across Google Cloud Platform services\"),\n" +
            "servicecontrol(\"https://www.googleapis.com/auth/servicecontrol\", \"Manage your Google Service Control data\"),\n" +
            "cloud-platform(\"https://www.googleapis.com/auth/cloud-platform\", \"View and manage your data across Google Cloud Platform services\"),\n" +
            "cloud-platform(\"https://www.googleapis.com/auth/cloud-platform\", \"View and manage your data across Google Cloud Platform services\"),\n" +
            "cloud-platform.read-only(\"https://www.googleapis.com/auth/cloud-platform.read-only\", \"View your data across Google Cloud Platform services\"),\n" +
            "service.management(\"https://www.googleapis.com/auth/service.management\", \"Manage your Google API service configuration\"),\n" +
            "service.management.readonly(\"https://www.googleapis.com/auth/service.management.readonly\", \"View your Google API service configuration\"),\n" +
            "cloud-platform(\"https://www.googleapis.com/auth/cloud-platform\", \"View and manage your data across Google Cloud Platform services\"),\n" +
            "service.management(\"https://www.googleapis.com/auth/service.management\", \"Manage your Google API service configuration\"),\n" +
            "cloud-platform(\"https://www.googleapis.com/auth/cloud-platform\", \"View and manage your data across Google Cloud Platform services\"),\n" +
            "cloud-platform.read-only(\"https://www.googleapis.com/auth/cloud-platform.read-only\", \"View your data across Google Cloud Platform services\"),\n" +
            "service.management(\"https://www.googleapis.com/auth/service.management\", \"Manage your Google API service configuration\"),\n" +
            "cloud-platform(\"https://www.googleapis.com/auth/cloud-platform\", \"View and manage your data across Google Cloud Platform services\"),\n" +
            "monitoring(\"https://www.googleapis.com/auth/monitoring\", \"View and write monitoring data for all of your Google and third-party Cloud and API projects\"),\n" +
            "monitoring.write(\"https://www.googleapis.com/auth/monitoring.write\", \"Publish metric data to your Google Cloud projects\"),\n" +
            "cloud-platform(\"https://www.googleapis.com/auth/cloud-platform\", \"View and manage your data across Google Cloud Platform services\"),\n" +
            "streetviewpublish(\"https://www.googleapis.com/auth/streetviewpublish\", \"Publish and manage your 360 photos on Google Street View\"),\n" +
            "tagmanager.delete.containers(\"https://www.googleapis.com/auth/tagmanager.delete.containers\", \"Delete your Google Tag Manager containers\"),\n" +
            "tagmanager.edit.containers(\"https://www.googleapis.com/auth/tagmanager.edit.containers\", \"Manage your Google Tag Manager container and its subcomponents, excluding versioning and publishing\"),\n" +
            "tagmanager.edit.containerversions(\"https://www.googleapis.com/auth/tagmanager.edit.containerversions\", \"Manage your Google Tag Manager container versions\"),\n" +
            "tagmanager.manage.accounts(\"https://www.googleapis.com/auth/tagmanager.manage.accounts\", \"View and manage your Google Tag Manager accounts\"),\n" +
            "tagmanager.manage.users(\"https://www.googleapis.com/auth/tagmanager.manage.users\", \"Manage user permissions of your Google Tag Manager account and container\"),\n" +
            "tagmanager.publish(\"https://www.googleapis.com/auth/tagmanager.publish\", \"Publish your Google Tag Manager container versions\"),\n" +
            "tagmanager.readonly(\"https://www.googleapis.com/auth/tagmanager.readonly\", \"View your Google Tag Manager container and its subcomponents\"),\n" +
            "tasks(\"https://www.googleapis.com/auth/tasks\", \"Create, edit, organize, and delete all your tasks\"),\n" +
            "tasks.readonly(\"https://www.googleapis.com/auth/tasks.readonly\", \"View your tasks\"),\n" +
            "cloud-platform(\"https://www.googleapis.com/auth/cloud-platform\", \"View and manage your data across Google Cloud Platform services\"),\n" +
            "youtube(\"https://www.googleapis.com/auth/youtube\", \"Manage your YouTube account\"),\n" +
            "youtube.readonly(\"https://www.googleapis.com/auth/youtube.readonly\", \"View your YouTube account\"),\n" +
            "youtubepartner(\"https://www.googleapis.com/auth/youtubepartner\", \"View and manage your assets and associated content on YouTube\"),\n" +
            "yt-analytics-monetary.readonly(\"https://www.googleapis.com/auth/yt-analytics-monetary.readonly\", \"View monetary and non-monetary YouTube Analytics reports for your YouTube content\"),\n" +
            "yt-analytics.readonly(\"https://www.googleapis.com/auth/yt-analytics.readonly\", \"View YouTube Analytics reports for your YouTube content\"),\n" +
            "youtube(\"https://www.googleapis.com/auth/youtube\", \"Manage your YouTube account\"),\n" +
            "youtube.channel-memberships.creator(\"https://www.googleapis.com/auth/youtube.channel-memberships.creator\", \"See a list of your current active channel members, their current level, and when they became a member\"),\n" +
            "youtube.force-ssl(\"https://www.googleapis.com/auth/youtube.force-ssl\", \"See, edit, and permanently delete your YouTube videos, ratings, comments and captions\"),\n" +
            "youtube.readonly(\"https://www.googleapis.com/auth/youtube.readonly\", \"View your YouTube account\"),\n" +
            "youtube.upload(\"https://www.googleapis.com/auth/youtube.upload\", \"Manage your YouTube videos\"),\n" +
            "youtubepartner(\"https://www.googleapis.com/auth/youtubepartner\", \"View and manage your assets and associated content on YouTube\"),\n" +
            "youtubepartner-channel-audit(\"https://www.googleapis.com/auth/youtubepartner-channel-audit\", \"View private information of your YouTube channel relevant during the audit process with a YouTube partner\"),\n" +
            "yt-analytics-monetary.readonly(\"https://www.googleapis.com/auth/yt-analytics-monetary.readonly\", \"View monetary and non-monetary YouTube Analytics reports for your YouTube content\"),\n" +
            "yt-analytics.readonly(\"https://www.googleapis.com/auth/yt-analytics.readonly\", \"View YouTube Analytics reports for your YouTube content\"),";
        List<String> scopes = Arrays.stream(scopeStr.split("\n")).distinct().sorted(Comparator.reverseOrder()).collect(Collectors.toList());
        System.out.println(scopes.size());
        for (String s : scopes) {
            String name = s.substring(0, s.indexOf("("));
            String scope = s.substring(s.indexOf("("));
            name = name.replaceAll("\\.", "-")
                .replaceAll("-", "_")
                .toUpperCase();
            System.out.println(name + scope);
        }
    }
}
