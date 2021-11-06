// Copyright 2020 Google LLC
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
//     https://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.

package com.example.demo;

import com.google.ads.googleads.lib.GoogleAdsClient;
import com.google.ads.googleads.v8.services.GoogleAdsServiceClient;
import com.google.auth.oauth2.UserCredentials;

/**
 * Shows how to configure the client library programmatically.
 *
 * <p>This is useful when you authenticate with many sets of credentials. For example, if you
 * authenticate on behalf of third-party advertisers.
 */
public class TestAPI {

  private static final String DEVELOPER_TOKEN =
  private static final String OAUTH_CLIENT_ID =
  private static final String OAUTH_CLIENT_SECRET =
  private static final String REFRESH_TOKEN =
  private static final String OPERATING_CUSTOMER_ID =
  private static final String LOGIN_CUSTOMER_ID = 

  public static void main(String[] args) {
    // Sets up the credentials for Google Ads user authentication.
    UserCredentials credentials =
        UserCredentials.newBuilder()
            .setClientId(OAUTH_CLIENT_ID)
            .setClientSecret(OAUTH_CLIENT_SECRET)
            .setRefreshToken(REFRESH_TOKEN)
            .build();

    // Creates a GoogleAdsClient with the provided credentials.
    GoogleAdsClient client =
        GoogleAdsClient.newBuilder()
            // Sets the developer token which enables API access.
            .setDeveloperToken(DEVELOPER_TOKEN)
            // Sets the OAuth credentials which provide Google Ads account access.
            .setCredentials(credentials)
            // Optional: sets the login customer ID. This is required when the Google account
            // authenticated with the refresh token does not have direct access to
            // OPERATING_CUSTOMER_ID and the access is via a manager account. In this case, specify
            // the manager account ID as LOGIN_CUSTOMER_ID.
            .setLoginCustomerId(Long.valueOf(LOGIN_CUSTOMER_ID))
            .build();
    String query = "SELECT " +
            "  campaign.id, " +
            "  campaign.name, " +
            "  campaign.advertising_channel_type, " +
            "  campaign.advertising_channel_sub_type, " +
            "  campaign.app_campaign_setting.app_id, " +
            "  campaign.app_campaign_setting.app_store, " +
            "  campaign.app_campaign_setting.bidding_strategy_goal_type, " +
            "  segments.date,  " +
            "  metrics.impressions,  " +
            "  metrics.clicks " +
            " FROM campaign  " +
            " WHERE " +
            "  campaign.advertising_channel_type = 'MULTI_CHANNEL' " +
            "  AND campaign.advertising_channel_sub_type IN " +
            "      ('APP_CAMPAIGN', 'APP_CAMPAIGN_FOR_ENGAGEMENT') " +
            "  AND segments.date DURING LAST_30_DAYS ";

    // Uses the client configured with these credentials.
    try (GoogleAdsServiceClient googleAdsServiceClient =
        client.getLatestVersion().createGoogleAdsServiceClient()) {
      GoogleAdsServiceClient.SearchPagedResponse result =  googleAdsServiceClient.search(OPERATING_CUSTOMER_ID, query);
    }
  }
}
