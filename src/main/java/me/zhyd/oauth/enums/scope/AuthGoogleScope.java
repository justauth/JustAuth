package me.zhyd.oauth.enums.scope;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Google 平台 OAuth 授权范围
 *
 * @author yadong.zhang (yadong.zhang0415(a)gmail.com)
 * @version 1.0.0
 * @since 1.0.0
 */
@Getter
@AllArgsConstructor
public enum AuthGoogleScope implements AuthScope {

    /**
     * {@code scope} 含义，以{@code description} 为准
     */
    USER_OPENID("openid", "Associate you with your personal info on Google", true),
    USER_EMAIL("email", "View your email address", true),
    USER_PROFILE("profile", "View your basic profile info", true),
    USER_PHONENUMBERS_READ("https://www.googleapis.com/auth/user.phonenumbers.read", "View your phone numbers", false),
    USER_ORGANIZATION_READ("https://www.googleapis.com/auth/user.organization.read", "See your education, work history and org info", false),
    USER_GENDER_READ("https://www.googleapis.com/auth/user.gender.read", "See your gender", false),
    USER_EMAILS_READ("https://www.googleapis.com/auth/user.emails.read", "View your email addresses", false),
    USER_BIRTHDAY_READ("https://www.googleapis.com/auth/user.birthday.read", "View your complete date of birth", false),
    USER_ADDRESSES_READ("https://www.googleapis.com/auth/user.addresses.read", "View your street addresses", false),
    USERINFO_PROFILE("https://www.googleapis.com/auth/userinfo.profile", "See your personal info, including any personal info you've made publicly available", false),
    USERINFO_EMAIL("https://www.googleapis.com/auth/userinfo.email", "View your email address", false),
    YT_ANALYTICS_READONLY("https://www.googleapis.com/auth/yt-analytics.readonly", "View YouTube Analytics reports for your YouTube content", false),
    YT_ANALYTICS_MONETARY_READONLY("https://www.googleapis.com/auth/yt-analytics-monetary.readonly", "View monetary and non-monetary YouTube Analytics reports for your YouTube content", false),
    YOUTUBEPARTNER_CHANNEL_AUDIT("https://www.googleapis.com/auth/youtubepartner-channel-audit", "View private information of your YouTube channel relevant during the audit process with a YouTube partner", false),
    YOUTUBEPARTNER("https://www.googleapis.com/auth/youtubepartner", "View and manage your assets and associated content on YouTube", false),
    YOUTUBE_UPLOAD("https://www.googleapis.com/auth/youtube.upload", "Manage your YouTube videos", false),
    YOUTUBE_READONLY("https://www.googleapis.com/auth/youtube.readonly", "View your YouTube account", false),
    YOUTUBE_FORCE_SSL("https://www.googleapis.com/auth/youtube.force-ssl", "See, edit, and permanently delete your YouTube videos, ratings, comments and captions", false),
    YOUTUBE_CHANNEL_MEMBERSHIPS_CREATOR("https://www.googleapis.com/auth/youtube.channel-memberships.creator", "See a list of your current active channel members, their current level, and when they became a member", false),
    YOUTUBE("https://www.googleapis.com/auth/youtube", "Manage your YouTube account", false),
    WEBMASTERS_READONLY("https://www.googleapis.com/auth/webmasters.readonly", "View Search Console data for your verified sites", false),
    WEBMASTERS("https://www.googleapis.com/auth/webmasters", "View and manage Search Console data for your verified sites", false),
    VERIFIEDACCESS("https://www.googleapis.com/auth/verifiedaccess", "Verify your enterprise credentials", false),
    TRACE_APPEND("https://www.googleapis.com/auth/trace.append", "Write Trace data for a project or application", false),
    TASKS_READONLY("https://www.googleapis.com/auth/tasks.readonly", "View your tasks", false),
    TASKS("https://www.googleapis.com/auth/tasks", "Create, edit, organize, and delete all your tasks", false),
    TAGMANAGER_READONLY("https://www.googleapis.com/auth/tagmanager.readonly", "View your Google Tag Manager container and its subcomponents", false),
    TAGMANAGER_PUBLISH("https://www.googleapis.com/auth/tagmanager.publish", "Publish your Google Tag Manager container versions", false),
    TAGMANAGER_MANAGE_USERS("https://www.googleapis.com/auth/tagmanager.manage.users", "Manage user permissions of your Google Tag Manager account and container", false),
    TAGMANAGER_MANAGE_ACCOUNTS("https://www.googleapis.com/auth/tagmanager.manage.accounts", "View and manage your Google Tag Manager accounts", false),
    TAGMANAGER_EDIT_CONTAINERVERSIONS("https://www.googleapis.com/auth/tagmanager.edit.containerversions", "Manage your Google Tag Manager container versions", false),
    TAGMANAGER_EDIT_CONTAINERS("https://www.googleapis.com/auth/tagmanager.edit.containers", "Manage your Google Tag Manager container and its subcomponents, excluding versioning and publishing", false),
    TAGMANAGER_DELETE_CONTAINERS("https://www.googleapis.com/auth/tagmanager.delete.containers", "Delete your Google Tag Manager containers", false),
    STREETVIEWPUBLISH("https://www.googleapis.com/auth/streetviewpublish", "Publish and manage your 360 photos on Google Street View", false),
    SQLSERVICE_ADMIN("https://www.googleapis.com/auth/sqlservice.admin", "Manage your Google SQL Service instances", false),
    SPREADSHEETS_READONLY("https://www.googleapis.com/auth/spreadsheets.readonly", "View your Google Spreadsheets", false),
    SPREADSHEETS("https://www.googleapis.com/auth/spreadsheets", "See, edit, create, and delete your spreadsheets in Google Drive", false),
    SPANNER_DATA("https://www.googleapis.com/auth/spanner.data", "View and manage the contents of your Spanner databases", false),
    SPANNER_ADMIN("https://www.googleapis.com/auth/spanner.admin", "Administer your Spanner databases", false),
    SOURCE_READ_WRITE("https://www.googleapis.com/auth/source.read_write", "Manage the contents of your source code repositories", false),
    SOURCE_READ_ONLY("https://www.googleapis.com/auth/source.read_only", "View the contents of your source code repositories", false),
    SOURCE_FULL_CONTROL("https://www.googleapis.com/auth/source.full_control", "Manage your source code repositories", false),
    SITEVERIFICATION_VERIFY_ONLY("https://www.googleapis.com/auth/siteverification.verify_only", "Manage your new site verifications with Google", false),
    SITEVERIFICATION("https://www.googleapis.com/auth/siteverification", "Manage the list of sites and domains you control", false),
    SERVICECONTROL("https://www.googleapis.com/auth/servicecontrol", "Manage your Google Service Control data", false),
    SERVICE_MANAGEMENT_READONLY("https://www.googleapis.com/auth/service.management.readonly", "View your Google API service configuration", false),
    SERVICE_MANAGEMENT("https://www.googleapis.com/auth/service.management", "Manage your Google API service configuration", false),
    SCRIPT_PROJECTS_READONLY("https://www.googleapis.com/auth/script.projects.readonly", "View Google Apps Script projects", false),
    SCRIPT_PROJECTS("https://www.googleapis.com/auth/script.projects", "Create and update Google Apps Script projects", false),
    SCRIPT_PROCESSES("https://www.googleapis.com/auth/script.processes", "View Google Apps Script processes", false),
    SCRIPT_METRICS("https://www.googleapis.com/auth/script.metrics", "View Google Apps Script project's metrics", false),
    SCRIPT_DEPLOYMENTS_READONLY("https://www.googleapis.com/auth/script.deployments.readonly", "View Google Apps Script deployments", false),
    SCRIPT_DEPLOYMENTS("https://www.googleapis.com/auth/script.deployments", "Create and update Google Apps Script deployments", false),
    PUBSUB("https://www.googleapis.com/auth/pubsub", "View and manage Pub/Sub topics and subscriptions", false),
    PRESENTATIONS_READONLY("https://www.googleapis.com/auth/presentations.readonly", "View your Google Slides presentations", false),
    PRESENTATIONS("https://www.googleapis.com/auth/presentations", "View and manage your Google Slides presentations", false),
    PHOTOSLIBRARY_SHARING("https://www.googleapis.com/auth/photoslibrary.sharing", "Manage and add to shared albums on your behalf", false),
    PHOTOSLIBRARY_READONLY_APPCREATEDDATA("https://www.googleapis.com/auth/photoslibrary.readonly.appcreateddata", "Manage photos added by this app", false),
    PHOTOSLIBRARY_READONLY("https://www.googleapis.com/auth/photoslibrary.readonly", "View your Google Photos library", false),
    PHOTOSLIBRARY_APPENDONLY("https://www.googleapis.com/auth/photoslibrary.appendonly", "Add to your Google Photos library", false),
    PHOTOSLIBRARY("https://www.googleapis.com/auth/photoslibrary", "View and manage your Google Photos library", false),
    NDEV_CLOUDMAN_READONLY("https://www.googleapis.com/auth/ndev.cloudman.readonly", "View your Google Cloud Platform management resources and deployment status information", false),
    NDEV_CLOUDMAN("https://www.googleapis.com/auth/ndev.cloudman", "View and manage your Google Cloud Platform management resources and deployment status information", false),
    NDEV_CLOUDDNS_READWRITE("https://www.googleapis.com/auth/ndev.clouddns.readwrite", "View and manage your DNS records hosted by Google Cloud DNS", false),
    NDEV_CLOUDDNS_READONLY("https://www.googleapis.com/auth/ndev.clouddns.readonly", "View your DNS records hosted by Google Cloud DNS", false),
    MONITORING_WRITE("https://www.googleapis.com/auth/monitoring.write", "Publish metric data to your Google Cloud projects", false),
    MONITORING_READ("https://www.googleapis.com/auth/monitoring.read", "View monitoring data for all of your Google Cloud and third-party projects", false),
    MONITORING("https://www.googleapis.com/auth/monitoring", "View and write monitoring data for all of your Google and third-party Cloud and API projects", false),
    MANUFACTURERCENTER("https://www.googleapis.com/auth/manufacturercenter", "Manage your product listings for Google Manufacturer Center", false),
    LOGGING_WRITE("https://www.googleapis.com/auth/logging.write", "Submit log data for your projects", false),
    LOGGING_READ("https://www.googleapis.com/auth/logging.read", "View log data for your projects", false),
    LOGGING_ADMIN("https://www.googleapis.com/auth/logging.admin", "Administrate log data for your projects", false),
    JOBS("https://www.googleapis.com/auth/jobs", "Manage job postings", false),
    INDEXING("https://www.googleapis.com/auth/indexing", "Submit data to Google for indexing", false),
    GROUPS("https://www.googleapis.com/auth/groups", "View and manage your Google Groups", false),
    GMAIL("https://mail.google.com/", "Read, compose, send, and permanently delete all your email from Gmail", false),
    GMAIL_SETTINGS_SHARING("https://www.googleapis.com/auth/gmail.settings.sharing", "Manage your sensitive mail settings, including who can manage your mail", false),
    GMAIL_SETTINGS_BASIC("https://www.googleapis.com/auth/gmail.settings.basic", "Manage your basic mail settings", false),
    GMAIL_SEND("https://www.googleapis.com/auth/gmail.send", "Send email on your behalf", false),
    GMAIL_READONLY("https://www.googleapis.com/auth/gmail.readonly", "View your email messages and settings", false),
    GMAIL_MODIFY("https://www.googleapis.com/auth/gmail.modify", "View and modify but not delete your email", false),
    GMAIL_METADATA("https://www.googleapis.com/auth/gmail.metadata", "View your email message metadata such as labels and headers, but not the email body", false),
    GMAIL_LABELS("https://www.googleapis.com/auth/gmail.labels", "Manage mailbox labels", false),
    GMAIL_INSERT("https://www.googleapis.com/auth/gmail.insert", "Insert mail into your mailbox", false),
    GMAIL_COMPOSE("https://www.googleapis.com/auth/gmail.compose", "Manage drafts and send emails", false),
    GMAIL_ADDONS_CURRENT_MESSAGE_READONLY("https://www.googleapis.com/auth/gmail.addons.current.message.readonly", "View your email messages when the add-on is running", false),
    GMAIL_ADDONS_CURRENT_MESSAGE_METADATA("https://www.googleapis.com/auth/gmail.addons.current.message.metadata", "View your email message metadata when the add-on is running", false),
    GMAIL_ADDONS_CURRENT_MESSAGE_ACTION("https://www.googleapis.com/auth/gmail.addons.current.message.action", "View your email messages when you interact with the add-on", false),
    GMAIL_ADDONS_CURRENT_ACTION_COMPOSE("https://www.googleapis.com/auth/gmail.addons.current.action.compose", "Manage drafts and send emails when you interact with the add-on", false),
    GENOMICS("https://www.googleapis.com/auth/genomics", "View and manage Genomics data", false),
    GAMES("https://www.googleapis.com/auth/games", "Create, edit, and delete your Google Play Games activity", false),
    FORMS_CURRENTONLY("https://www.googleapis.com/auth/forms.currentonly", "View and manage forms that this application has been installed in", false),
    FORMS("https://www.googleapis.com/auth/forms", "View and manage your forms in Google Drive", false),
    FITNESS_REPRODUCTIVE_HEALTH_WRITE("https://www.googleapis.com/auth/fitness.reproductive_health.write", "See and add info about your reproductive health in Google Fit. I consent to Google sharing my reporductive health information with this app.", false),
    FITNESS_REPRODUCTIVE_HEALTH_READ("https://www.googleapis.com/auth/fitness.reproductive_health.read", "See info about your reproductive health in Google Fit. I consent to Google sharing my reporductive health information with this app.", false),
    FITNESS_OXYGEN_SATURATION_WRITE("https://www.googleapis.com/auth/fitness.oxygen_saturation.write", "See and add info about your oxygen saturation in Google Fit. I consent to Google sharing my oxygen saturation information with this app.", false),
    FITNESS_OXYGEN_SATURATION_READ("https://www.googleapis.com/auth/fitness.oxygen_saturation.read", "See info about your oxygen saturation in Google Fit. I consent to Google sharing my oxygen saturation information with this app.", false),
    FITNESS_NUTRITION_WRITE("https://www.googleapis.com/auth/fitness.nutrition.write", "See and add to info about your nutrition in Google Fit", false),
    FITNESS_NUTRITION_READ("https://www.googleapis.com/auth/fitness.nutrition.read", "See info about your nutrition in Google Fit", false),
    FITNESS_LOCATION_WRITE("https://www.googleapis.com/auth/fitness.location.write", "See and add to your Google Fit location data", false),
    FITNESS_LOCATION_READ("https://www.googleapis.com/auth/fitness.location.read", "See your Google Fit speed and distance data", false),
    FITNESS_BODY_TEMPERATURE_WRITE("https://www.googleapis.com/auth/fitness.body_temperature.write", "See and add to info about your body temperature in Google Fit. I consent to Google sharing my body temperature information with this app.", false),
    FITNESS_BODY_TEMPERATURE_READ("https://www.googleapis.com/auth/fitness.body_temperature.read", "See info about your body temperature in Google Fit. I consent to Google sharing my body temperature information with this app.", false),
    FITNESS_BODY_WRITE("https://www.googleapis.com/auth/fitness.body.write", "See and add info about your body measurements and heart rate to Google Fit", false),
    FITNESS_BODY_READ("https://www.googleapis.com/auth/fitness.body.read", "See info about your body measurements and heart rate in Google Fit", false),
    FITNESS_BLOOD_PRESSURE_WRITE("https://www.googleapis.com/auth/fitness.blood_pressure.write", "See and add info about your blood pressure in Google Fit. I consent to Google sharing my blood pressure information with this app.", false),
    FITNESS_BLOOD_PRESSURE_READ("https://www.googleapis.com/auth/fitness.blood_pressure.read", "See info about your blood pressure in Google Fit. I consent to Google sharing my blood pressure information with this app.", false),
    FITNESS_BLOOD_GLUCOSE_WRITE("https://www.googleapis.com/auth/fitness.blood_glucose.write", "See and add info about your blood glucose to Google Fit. I consent to Google sharing my blood glucose information with this app.", false),
    FITNESS_BLOOD_GLUCOSE_READ("https://www.googleapis.com/auth/fitness.blood_glucose.read", "See info about your blood glucose in Google Fit. I consent to Google sharing my blood glucose information with this app.", false),
    FITNESS_ACTIVITY_WRITE("https://www.googleapis.com/auth/fitness.activity.write", "See and add to your Google Fit physical activity data", false),
    FITNESS_ACTIVITY_READ("https://www.googleapis.com/auth/fitness.activity.read", "Use Google Fit to see and store your physical activity data", false),
    FIREBASE_READONLY("https://www.googleapis.com/auth/firebase.readonly", "View all your Firebase data and settings", false),
    FIREBASE("https://www.googleapis.com/auth/firebase", "View and administer all your Firebase data and settings", false),
    EDISCOVERY_READONLY("https://www.googleapis.com/auth/ediscovery.readonly", "View your eDiscovery data", false),
    EDISCOVERY("https://www.googleapis.com/auth/ediscovery", "Manage your eDiscovery data", false),
    DRIVE_SCRIPTS("https://www.googleapis.com/auth/drive.scripts", "Modify your Google Apps Script scripts' behavior", false),
    DRIVE_READONLY("https://www.googleapis.com/auth/drive.readonly", "See and download all your Google Drive files", false),
    DRIVE_PHOTOS_READONLY("https://www.googleapis.com/auth/drive.photos.readonly", "View the photos, videos and albums in your Google Photos", false),
    DRIVE_METADATA_READONLY("https://www.googleapis.com/auth/drive.metadata.readonly", "View metadata for files in your Google Drive", false),
    DRIVE_METADATA("https://www.googleapis.com/auth/drive.metadata", "View and manage metadata of files in your Google Drive", false),
    DRIVE_FILE("https://www.googleapis.com/auth/drive.file", "View and manage Google Drive files and folders that you have opened or created with this app", false),
    DRIVE_APPDATA("https://www.googleapis.com/auth/drive.appdata", "View and manage its own configuration data in your Google Drive", false),
    DRIVE_ACTIVITY_READONLY("https://www.googleapis.com/auth/drive.activity.readonly", "View the activity record of files in your Google Drive", false),
    DRIVE_ACTIVITY("https://www.googleapis.com/auth/drive.activity", "View and add to the activity record of files in your Google Drive", false),
    DRIVE("https://www.googleapis.com/auth/drive", "See, edit, create, and delete all of your Google Drive files", false),
    ACTIVITY("https://www.googleapis.com/auth/activity", "View the activity history of your Google apps", false),
    DOUBLECLICKSEARCH("https://www.googleapis.com/auth/doubleclicksearch", "View and manage your advertising data in DoubleClick Search", false),
    DOUBLECLICKBIDMANAGER("https://www.googleapis.com/auth/doubleclickbidmanager", "View and manage your reports in DoubleClick Bid Manager", false),
    DOCUMENTS_READONLY("https://www.googleapis.com/auth/documents.readonly", "View your Google Docs documents", false),
    DOCUMENTS("https://www.googleapis.com/auth/documents", "View and manage your Google Docs documents", false),
    DISPLAY_VIDEO("https://www.googleapis.com/auth/display-video", "Create, see, edit, and permanently delete your Display & Video 360 entities and reports", false),
    DIRECTORY_READONLY("https://www.googleapis.com/auth/directory.readonly", "See and download your organization's GSuite directory", false),
    DIALOGFLOW("https://www.googleapis.com/auth/dialogflow", "View, manage and query your Dialogflow agents", false),
    DFATRAFFICKING("https://www.googleapis.com/auth/dfatrafficking", "View and manage your DoubleClick Campaign Manager's (DCM) display ad campaigns", false),
    DFAREPORTING("https://www.googleapis.com/auth/dfareporting", "View and manage DoubleClick for Advertisers reports", false),
    DEVSTORAGE_READ_WRITE("https://www.googleapis.com/auth/devstorage.read_write", "Manage your data in Google Cloud Storage", false),
    DEVSTORAGE_READ_ONLY("https://www.googleapis.com/auth/devstorage.read_only", "View your data in Google Cloud Storage", false),
    DEVSTORAGE_FULL_CONTROL("https://www.googleapis.com/auth/devstorage.full_control", "Manage your data and permissions in Google Cloud Storage", false),
    DDMCONVERSIONS("https://www.googleapis.com/auth/ddmconversions", "Manage DoubleClick Digital Marketing conversions", false),
    DATASTORE("https://www.googleapis.com/auth/datastore", "View and manage your Google Cloud Datastore data", false),
    CONTENT("https://www.googleapis.com/auth/content", "Manage your product listings and accounts for Google Shopping", false),
    CONTACTS_READONLY("https://www.googleapis.com/auth/contacts.readonly", "See and download your contacts", false),
    CONTACTS_OTHER_READONLY("https://www.googleapis.com/auth/contacts.other.readonly", "See and download contact info automatically saved in your \"Other contacts\"", false),
    CONTACTS("https://www.googleapis.com/auth/contacts", "See, edit, download, and permanently delete your contacts", false),
    CONTACTS_FEEDS("https://www.google.com/m8/feeds", "See, edit, download, and permanently delete your contacts", false),
    COMPUTE_READONLY("https://www.googleapis.com/auth/compute.readonly", "View your Google Compute Engine resources", false),
    COMPUTE("https://www.googleapis.com/auth/compute", "View and manage your Google Compute Engine resources", false),
    CLOUDRUNTIMECONFIG("https://www.googleapis.com/auth/cloudruntimeconfig", "Manage your Google Cloud Platform services' runtime configuration", false),
    CLOUDKMS("https://www.googleapis.com/auth/cloudkms", "View and manage your keys and secrets stored in Cloud Key Management Service", false),
    CLOUDIOT("https://www.googleapis.com/auth/cloudiot", "Register and manage devices in the Google Cloud IoT service", false),
    CLOUD_SEARCH_STATS_INDEXING("https://www.googleapis.com/auth/cloud_search.stats.indexing", "Index and serve your organization's data with Cloud Search", false),
    CLOUD_SEARCH_STATS("https://www.googleapis.com/auth/cloud_search.stats", "Index and serve your organization's data with Cloud Search", false),
    CLOUD_SEARCH_SETTINGS_QUERY("https://www.googleapis.com/auth/cloud_search.settings.query", "Index and serve your organization's data with Cloud Search", false),
    CLOUD_SEARCH_SETTINGS_INDEXING("https://www.googleapis.com/auth/cloud_search.settings.indexing", "Index and serve your organization's data with Cloud Search", false),
    CLOUD_SEARCH_SETTINGS("https://www.googleapis.com/auth/cloud_search.settings", "Index and serve your organization's data with Cloud Search", false),
    CLOUD_SEARCH_QUERY("https://www.googleapis.com/auth/cloud_search.query", "Search your organization's data in the Cloud Search index", false),
    CLOUD_SEARCH_INDEXING("https://www.googleapis.com/auth/cloud_search.indexing", "Index and serve your organization's data with Cloud Search", false),
    CLOUD_SEARCH_DEBUG("https://www.googleapis.com/auth/cloud_search.debug", "Index and serve your organization's data with Cloud Search", false),
    CLOUD_SEARCH("https://www.googleapis.com/auth/cloud_search", "Index and serve your organization's data with Cloud Search", false),
    CLOUD_DEBUGGER("https://www.googleapis.com/auth/cloud_debugger", "Use Stackdriver Debugger", false),
    CLOUD_VISION("https://www.googleapis.com/auth/cloud-vision", "Apply machine learning models to understand and label images", false),
    CLOUD_TRANSLATION("https://www.googleapis.com/auth/cloud-translation", "Translate text from one language to another using Google Translate", false),
    CLOUD_PLATFORM_READ_ONLY("https://www.googleapis.com/auth/cloud-platform.read-only", "View your data across Google Cloud Platform services", false),
    CLOUD_PLATFORM("https://www.googleapis.com/auth/cloud-platform", "View and manage your data across Google Cloud Platform services", false),
    CLOUD_LANGUAGE("https://www.googleapis.com/auth/cloud-language", "Apply machine learning models to reveal the structure and meaning of text", false),
    CLOUD_IDENTITY_GROUPS_READONLY("https://www.googleapis.com/auth/cloud-identity.groups.readonly", "See any Cloud Identity Groups that you can access, including group members and their emails", false),
    CLOUD_IDENTITY_GROUPS("https://www.googleapis.com/auth/cloud-identity.groups", "See, change, create, and delete any of the Cloud Identity Groups that you can access, including the members of each group", false),
    CLOUD_BIGTABLE_ADMIN_TABLE("https://www.googleapis.com/auth/cloud-bigtable.admin.table", "Administer your Cloud Bigtable tables", false),
    CLOUD_BIGTABLE_ADMIN_CLUSTER("https://www.googleapis.com/auth/cloud-bigtable.admin.cluster", "Administer your Cloud Bigtable clusters", false),
    CLOUD_BIGTABLE_ADMIN("https://www.googleapis.com/auth/cloud-bigtable.admin", "Administer your Cloud Bigtable tables and clusters", false),
    CLASSROOM_TOPICS_READONLY("https://www.googleapis.com/auth/classroom.topics.readonly", "View topics in Google Classroom", false),
    CLASSROOM_TOPICS("https://www.googleapis.com/auth/classroom.topics", "See, create, and edit topics in Google Classroom", false),
    CLASSROOM_STUDENT_SUBMISSIONS_STUDENTS_READONLY("https://www.googleapis.com/auth/classroom.student-submissions.students.readonly", "View course work and grades for students in the Google Classroom classes you teach or administer", false),
    CLASSROOM_STUDENT_SUBMISSIONS_ME_READONLY("https://www.googleapis.com/auth/classroom.student-submissions.me.readonly", "View your course work and grades in Google Classroom", false),
    CLASSROOM_ROSTERS_READONLY("https://www.googleapis.com/auth/classroom.rosters.readonly", "View your Google Classroom class rosters", false),
    CLASSROOM_ROSTERS("https://www.googleapis.com/auth/classroom.rosters", "Manage your Google Classroom class rosters", false),
    CLASSROOM_PUSH_NOTIFICATIONS("https://www.googleapis.com/auth/classroom.push-notifications", "Receive notifications about your Google Classroom data", false),
    CLASSROOM_PROFILE_PHOTOS("https://www.googleapis.com/auth/classroom.profile.photos", "View the profile photos of people in your classes", false),
    CLASSROOM_PROFILE_EMAILS("https://www.googleapis.com/auth/classroom.profile.emails", "View the email addresses of people in your classes", false),
    CLASSROOM_GUARDIANLINKS_STUDENTS_READONLY("https://www.googleapis.com/auth/classroom.guardianlinks.students.readonly", "View guardians for students in your Google Classroom classes", false),
    CLASSROOM_GUARDIANLINKS_STUDENTS("https://www.googleapis.com/auth/classroom.guardianlinks.students", "View and manage guardians for students in your Google Classroom classes", false),
    CLASSROOM_GUARDIANLINKS_ME_READONLY("https://www.googleapis.com/auth/classroom.guardianlinks.me.readonly", "View your Google Classroom guardians", false),
    CLASSROOM_COURSEWORK_STUDENTS_READONLY("https://www.googleapis.com/auth/classroom.coursework.students.readonly", "View course work and grades for students in the Google Classroom classes you teach or administer", false),
    CLASSROOM_COURSEWORK_STUDENTS("https://www.googleapis.com/auth/classroom.coursework.students", "Manage course work and grades for students in the Google Classroom classes you teach and view the course work and grades for classes you administer", false),
    CLASSROOM_COURSEWORK_ME_READONLY("https://www.googleapis.com/auth/classroom.coursework.me.readonly", "View your course work and grades in Google Classroom", false),
    CLASSROOM_COURSEWORK_ME("https://www.googleapis.com/auth/classroom.coursework.me", "Manage your course work and view your grades in Google Classroom", false),
    CLASSROOM_COURSES_READONLY("https://www.googleapis.com/auth/classroom.courses.readonly", "View your Google Classroom classes", false),
    CLASSROOM_COURSES("https://www.googleapis.com/auth/classroom.courses", "Manage your Google Classroom classes", false),
    CLASSROOM_ANNOUNCEMENTS_READONLY("https://www.googleapis.com/auth/classroom.announcements.readonly", "View announcements in Google Classroom", false),
    CLASSROOM_ANNOUNCEMENTS("https://www.googleapis.com/auth/classroom.announcements", "View and manage announcements in Google Classroom", false),
    CALENDAR_SETTINGS_READONLY("https://www.googleapis.com/auth/calendar.settings.readonly", "View your Calendar settings", false),
    CALENDAR_READONLY("https://www.googleapis.com/auth/calendar.readonly", "View your calendars", false),
    CALENDAR_EVENTS_READONLY("https://www.googleapis.com/auth/calendar.events.readonly", "View events on all your calendars", false),
    CALENDAR_EVENTS("https://www.googleapis.com/auth/calendar.events", "View and edit events on all your calendars", false),
    CALENDAR("https://www.googleapis.com/auth/calendar", "See, edit, share, and permanently delete all the calendars you can access using Google Calendar", false),
    CALENDAR_FEEDS("https://www.google.com/calendar/feeds", "See, edit, share, and permanently delete all the calendars you can access using Google Calendar", false),
    BOOKS("https://www.googleapis.com/auth/books", "Manage your books", false),
    BLOGGER_READONLY("https://www.googleapis.com/auth/blogger.readonly", "View your Blogger account", false),
    BLOGGER("https://www.googleapis.com/auth/blogger", "Manage your Blogger account", false),
    BIGTABLE_ADMIN_TABLE("https://www.googleapis.com/auth/bigtable.admin.table", "Administer your Cloud Bigtable tables", false),
    BIGTABLE_ADMIN_INSTANCE("https://www.googleapis.com/auth/bigtable.admin.instance", "Administer your Cloud Bigtable clusters", false),
    BIGTABLE_ADMIN_CLUSTER("https://www.googleapis.com/auth/bigtable.admin.cluster", "Administer your Cloud Bigtable clusters", false),
    BIGTABLE_ADMIN("https://www.googleapis.com/auth/bigtable.admin", "Administer your Cloud Bigtable tables and clusters", false),
    BIGQUERY_READONLY("https://www.googleapis.com/auth/bigquery.readonly", "View your data in Google BigQuery", false),
    BIGQUERY_INSERTDATA("https://www.googleapis.com/auth/bigquery.insertdata", "Insert data into Google BigQuery", false),
    BIGQUERY("https://www.googleapis.com/auth/bigquery", "View and manage your data in Google BigQuery", false),
    APPS_ORDER_READONLY("https://www.googleapis.com/auth/apps.order.readonly", "Manage users on your domain", false),
    APPS_ORDER("https://www.googleapis.com/auth/apps.order", "Manage users on your domain", false),
    APPS_LICENSING("https://www.googleapis.com/auth/apps.licensing", "View and manage G Suite licenses for your domain", false),
    APPS_GROUPS_SETTINGS("https://www.googleapis.com/auth/apps.groups.settings", "View and manage the settings of a G Suite group", false),
    APPS_GROUPS_MIGRATION("https://www.googleapis.com/auth/apps.groups.migration", "Manage messages in groups on your domain", false),
    APPS_ALERTS("https://www.googleapis.com/auth/apps.alerts", "See and delete your domain's G Suite alerts, and send alert feedback", false),
    APPENGINE_ADMIN("https://www.googleapis.com/auth/appengine.admin", "View and manage your applications deployed on Google App Engine", false),
    ANDROIDPUBLISHER("https://www.googleapis.com/auth/androidpublisher", "View and manage your Google Play Developer account", false),
    ANDROIDMANAGEMENT("https://www.googleapis.com/auth/androidmanagement", "Manage Android devices and apps for your customers", false),
    ANDROIDENTERPRISE("https://www.googleapis.com/auth/androidenterprise", "Manage corporate Android devices", false),
    ANALYTICS_USER_DELETION("https://www.googleapis.com/auth/analytics.user.deletion", "Manage Google Analytics user deletion requests", false),
    ANALYTICS_READONLY("https://www.googleapis.com/auth/analytics.readonly", "View your Google Analytics data", false),
    ANALYTICS_PROVISION("https://www.googleapis.com/auth/analytics.provision", "Create a new Google Analytics account along with its default property and view", false),
    ANALYTICS_MANAGE_USERS_READONLY("https://www.googleapis.com/auth/analytics.manage.users.readonly", "View Google Analytics user permissions", false),
    ANALYTICS_MANAGE_USERS("https://www.googleapis.com/auth/analytics.manage.users", "Manage Google Analytics Account users by email address", false),
    ANALYTICS_EDIT("https://www.googleapis.com/auth/analytics.edit", "Edit Google Analytics management entities", false),
    ANALYTICS("https://www.googleapis.com/auth/analytics", "View and manage your Google Analytics data", false),
    ADSENSEHOST("https://www.googleapis.com/auth/adsensehost", "View and manage your AdSense host data and associated accounts", false),
    ADSENSE_READONLY("https://www.googleapis.com/auth/adsense.readonly", "View your AdSense data", false),
    ADSENSE("https://www.googleapis.com/auth/adsense", "View and manage your AdSense data", false),
    ADMIN_REPORTS_USAGE_READONLY("https://www.googleapis.com/auth/admin.reports.usage.readonly", "View usage reports for your G Suite domain", false),
    ADMIN_REPORTS_AUDIT_READONLY("https://www.googleapis.com/auth/admin.reports.audit.readonly", "View audit reports for your G Suite domain", false),
    ADMIN_DIRECTORY_USERSCHEMA_READONLY("https://www.googleapis.com/auth/admin.directory.userschema.readonly", "View user schemas on your domain", false),
    ADMIN_DIRECTORY_USERSCHEMA("https://www.googleapis.com/auth/admin.directory.userschema", "View and manage the provisioning of user schemas on your domain", false),
    ADMIN_DIRECTORY_USER_SECURITY("https://www.googleapis.com/auth/admin.directory.user.security", "Manage data access permissions for users on your domain", false),
    ADMIN_DIRECTORY_USER_READONLY("https://www.googleapis.com/auth/admin.directory.user.readonly", "View users on your domain", false),
    ADMIN_DIRECTORY_USER_ALIAS_READONLY("https://www.googleapis.com/auth/admin.directory.user.alias.readonly", "View user aliases on your domain", false),
    ADMIN_DIRECTORY_USER_ALIAS("https://www.googleapis.com/auth/admin.directory.user.alias", "View and manage user aliases on your domain", false),
    ADMIN_DIRECTORY_USER("https://www.googleapis.com/auth/admin.directory.user", "View and manage the provisioning of users on your domain", false),
    ADMIN_DIRECTORY_ROLEMANAGEMENT_READONLY("https://www.googleapis.com/auth/admin.directory.rolemanagement.readonly", "View delegated admin roles for your domain", false),
    ADMIN_DIRECTORY_ROLEMANAGEMENT("https://www.googleapis.com/auth/admin.directory.rolemanagement", "Manage delegated admin roles for your domain", false),
    ADMIN_DIRECTORY_RESOURCE_CALENDAR_READONLY("https://www.googleapis.com/auth/admin.directory.resource.calendar.readonly", "View calendar resources on your domain", false),
    ADMIN_DIRECTORY_RESOURCE_CALENDAR("https://www.googleapis.com/auth/admin.directory.resource.calendar", "View and manage the provisioning of calendar resources on your domain", false),
    ADMIN_DIRECTORY_ORGUNIT_READONLY("https://www.googleapis.com/auth/admin.directory.orgunit.readonly", "View organization units on your domain", false),
    ADMIN_DIRECTORY_ORGUNIT("https://www.googleapis.com/auth/admin.directory.orgunit", "View and manage organization units on your domain", false),
    ADMIN_DIRECTORY_NOTIFICATIONS("https://www.googleapis.com/auth/admin.directory.notifications", "View and manage notifications received on your domain", false),
    ADMIN_DIRECTORY_GROUP_READONLY("https://www.googleapis.com/auth/admin.directory.group.readonly", "View groups on your domain", false),
    ADMIN_DIRECTORY_GROUP_MEMBER_READONLY("https://www.googleapis.com/auth/admin.directory.group.member.readonly", "View group subscriptions on your domain", false),
    ADMIN_DIRECTORY_GROUP_MEMBER("https://www.googleapis.com/auth/admin.directory.group.member", "View and manage group subscriptions on your domain", false),
    ADMIN_DIRECTORY_GROUP("https://www.googleapis.com/auth/admin.directory.group", "View and manage the provisioning of groups on your domain", false),
    ADMIN_DIRECTORY_DOMAIN_READONLY("https://www.googleapis.com/auth/admin.directory.domain.readonly", "View domains related to your customers", false),
    ADMIN_DIRECTORY_DOMAIN("https://www.googleapis.com/auth/admin.directory.domain", "View and manage the provisioning of domains for your customers", false),
    ADMIN_DIRECTORY_DEVICE_MOBILE_READONLY("https://www.googleapis.com/auth/admin.directory.device.mobile.readonly", "View your mobile devices' metadata", false),
    ADMIN_DIRECTORY_DEVICE_MOBILE_ACTION("https://www.googleapis.com/auth/admin.directory.device.mobile.action", "Manage your mobile devices by performing administrative tasks", false),
    ADMIN_DIRECTORY_DEVICE_MOBILE("https://www.googleapis.com/auth/admin.directory.device.mobile", "View and manage your mobile devices' metadata", false),
    ADMIN_DIRECTORY_DEVICE_CHROMEOS_READONLY("https://www.googleapis.com/auth/admin.directory.device.chromeos.readonly", "View your Chrome OS devices' metadata", false),
    ADMIN_DIRECTORY_DEVICE_CHROMEOS("https://www.googleapis.com/auth/admin.directory.device.chromeos", "View and manage your Chrome OS devices' metadata", false),
    ADMIN_DIRECTORY_CUSTOMER_READONLY("https://www.googleapis.com/auth/admin.directory.customer.readonly", "View customer related information", false),
    ADMIN_DIRECTORY_CUSTOMER("https://www.googleapis.com/auth/admin.directory.customer", "View and manage customer related information", false),
    ADMIN_DATATRANSFER_READONLY("https://www.googleapis.com/auth/admin.datatransfer.readonly", "View data transfers between users in your organization", false),
    ADMIN_DATATRANSFER("https://www.googleapis.com/auth/admin.datatransfer", "View and manage data transfers between users in your organization", false),
    ADEXCHANGE_BUYER("https://www.googleapis.com/auth/adexchange.buyer", "Manage your Ad Exchange buyer account configuration", false),
    ;

    private final String scope;
    private final String description;
    private final boolean isDefault;


    public static List<String> getAdminDirectoryScopes() {
        return Arrays.stream(new AuthGoogleScope[]{
            ADMIN_DIRECTORY_USERSCHEMA_READONLY,
            ADMIN_DIRECTORY_USERSCHEMA,
            ADMIN_DIRECTORY_USER_SECURITY,
            ADMIN_DIRECTORY_USER_READONLY,
            ADMIN_DIRECTORY_USER_ALIAS_READONLY,
            ADMIN_DIRECTORY_USER_ALIAS,
            ADMIN_DIRECTORY_USER,
            ADMIN_DIRECTORY_ROLEMANAGEMENT_READONLY,
            ADMIN_DIRECTORY_ROLEMANAGEMENT,
            ADMIN_DIRECTORY_RESOURCE_CALENDAR_READONLY,
            ADMIN_DIRECTORY_RESOURCE_CALENDAR,
            ADMIN_DIRECTORY_ORGUNIT_READONLY,
            ADMIN_DIRECTORY_ORGUNIT,
            ADMIN_DIRECTORY_NOTIFICATIONS,
            ADMIN_DIRECTORY_GROUP_READONLY,
            ADMIN_DIRECTORY_GROUP_MEMBER_READONLY,
            ADMIN_DIRECTORY_GROUP_MEMBER,
            ADMIN_DIRECTORY_GROUP,
            ADMIN_DIRECTORY_DOMAIN_READONLY,
            ADMIN_DIRECTORY_DOMAIN,
            ADMIN_DIRECTORY_DEVICE_MOBILE_READONLY,
            ADMIN_DIRECTORY_DEVICE_MOBILE_ACTION,
            ADMIN_DIRECTORY_DEVICE_MOBILE,
            ADMIN_DIRECTORY_DEVICE_CHROMEOS_READONLY,
            ADMIN_DIRECTORY_DEVICE_CHROMEOS,
            ADMIN_DIRECTORY_CUSTOMER_READONLY,
            ADMIN_DIRECTORY_CUSTOMER
        }).map(AuthGoogleScope::getScope).collect(Collectors.toList());
    }

    /**
     * View And manage user's mail in Gmail.
     *
     * @return List
     */
    public static List<String> getGmailScopes() {
        return Arrays.stream(new AuthGoogleScope[]{
            GMAIL,
            GMAIL_SETTINGS_SHARING,
            GMAIL_SETTINGS_BASIC,
            GMAIL_SEND,
            GMAIL_READONLY,
            GMAIL_MODIFY,
            GMAIL_METADATA,
            GMAIL_LABELS,
            GMAIL_INSERT,
            GMAIL_COMPOSE,
            GMAIL_ADDONS_CURRENT_MESSAGE_READONLY,
            GMAIL_ADDONS_CURRENT_MESSAGE_METADATA,
            GMAIL_ADDONS_CURRENT_MESSAGE_ACTION,
            GMAIL_ADDONS_CURRENT_ACTION_COMPOSE
        }).map(AuthGoogleScope::getScope).collect(Collectors.toList());
    }


    /**
     * Used for OIDC authorization and certification
     *
     * @return List
     */
    public static List<String> getOidcScopes() {
        return Arrays.stream(new AuthGoogleScope[]{
            USER_OPENID,
            USER_EMAIL,
            USER_PROFILE
        }).map(AuthGoogleScope::getScope).collect(Collectors.toList());
    }

    /**
     * View And manage user's detail and Google Contacts.
     *
     * @return List
     */
    public static List<String> getPeopleScopes() {
        return Arrays.stream(new AuthGoogleScope[]{
            CONTACTS_READONLY,
            CONTACTS_OTHER_READONLY,
            CONTACTS,
            CONTACTS_FEEDS,
            DIRECTORY_READONLY,
            USER_PHONENUMBERS_READ,
            USER_ORGANIZATION_READ,
            USER_GENDER_READ,
            USER_EMAILS_READ,
            USER_BIRTHDAY_READ,
            USER_ADDRESSES_READ,
            USERINFO_PROFILE,
            USERINFO_EMAIL
        }).map(AuthGoogleScope::getScope).collect(Collectors.toList());
    }


    /**
     * View and manage user's photo library.
     *
     * @return List
     */
    public static List<String> getPhotosLibraryScopes() {
        return Arrays.stream(new AuthGoogleScope[]{
            PHOTOSLIBRARY_SHARING,
            PHOTOSLIBRARY_READONLY_APPCREATEDDATA,
            PHOTOSLIBRARY_READONLY,
            PHOTOSLIBRARY_APPENDONLY,
            PHOTOSLIBRARY
        }).map(AuthGoogleScope::getScope).collect(Collectors.toList());
    }

    /**
     * View And manage user's videos, activity and playlists.
     *
     * @return List
     */
    public static List<String> getYouTubeScopes() {
        return Arrays.stream(new AuthGoogleScope[]{
            YT_ANALYTICS_READONLY,
            YT_ANALYTICS_MONETARY_READONLY,
            YOUTUBEPARTNER_CHANNEL_AUDIT,
            YOUTUBEPARTNER,
            YOUTUBE_UPLOAD,
            YOUTUBE_READONLY,
            YOUTUBE_FORCE_SSL,
            YOUTUBE_CHANNEL_MEMBERSHIPS_CREATOR,
            YOUTUBE
        }).map(AuthGoogleScope::getScope).collect(Collectors.toList());
    }

    /**
     * View And manage user's Google Analytics.
     *
     * @return List
     */
    public static List<String> getGoogleAnalyticsScopes() {
        return Arrays.stream(new AuthGoogleScope[]{
            ANALYTICS_USER_DELETION,
            ANALYTICS_READONLY,
            ANALYTICS_PROVISION,
            ANALYTICS_MANAGE_USERS_READONLY,
            ANALYTICS_MANAGE_USERS,
            ANALYTICS_EDIT,
            ANALYTICS
        }).map(AuthGoogleScope::getScope).collect(Collectors.toList());
    }

    /**
     * View And manage user's calendars in Google Calendar.
     *
     * @return List
     */
    public static List<String> getCalendarScopes() {
        return Arrays.stream(new AuthGoogleScope[]{
            CALENDAR_SETTINGS_READONLY,
            CALENDAR_READONLY,
            CALENDAR_EVENTS_READONLY,
            CALENDAR_EVENTS,
            CALENDAR,
            CALENDAR_FEEDS
        }).map(AuthGoogleScope::getScope).collect(Collectors.toList());
    }

    /**
     * List, download, create, move, edit, share and search all of user's documents and files in Google Drive.
     *
     * @return List
     */
    public static List<String> getDriveScopes() {
        return Arrays.stream(new AuthGoogleScope[]{
            DRIVE_SCRIPTS,
            DRIVE_READONLY,
            DRIVE_PHOTOS_READONLY,
            DRIVE_METADATA_READONLY,
            DRIVE_METADATA,
            DRIVE_FILE,
            DRIVE_APPDATA,
            DRIVE_ACTIVITY_READONLY,
            DRIVE_ACTIVITY,
            DRIVE,
            ACTIVITY
        }).map(AuthGoogleScope::getScope).collect(Collectors.toList());
    }

}
