#
#           Travis CI & gradle.properties live in harmony
# 
# Android convention is to store your API keys in a local, non-versioned
# gradle.properties file. Travis CI doesn't allow users to upload pre-populated
# gradle.properties files to store this secret information, but instaed allows
# users to store such information as environment variables.
#
# This script creates a local gradle.properties file on current the Travis CI
# instance. It then reads environment variable $INSTAGRAM_ACCESS_TOKEN which a user
# has defined in their Travis CI project settings environment variables, and
# writes this value to the Travis CI instance's gradle.properties file.
# 
# You must execute this script via your .travis.yml as a pre-process dependency,
# so your gradle build process has access to all variables.
#
#  before_script:
#   - source environmentSetup.sh && copyEnvVarsToGradleProperties

#!/usr/bin/env bash

function copyEnvVarsToGradleProperties {
    GRADLE_PROPERTIES=$HOME"/.gradle/gradle.properties"
    export GRADLE_PROPERTIES
    echo "Gradle Properties should exist at $GRADLE_PROPERTIES"
    if [ ! -f "$GRADLE_PROPERTIES" ]; then
        echo "Gradle Properties does not exist"

        echo "Creating Gradle Properties file..."
        touch $GRADLE_PROPERTIES

        echo "Writing KEYS to gradle.properties..."
        echo "INSTAGRAM_ACCESS_TOKEN=$INSTAGRAM_ACCESS_TOKEN" >> $GRADLE_PROPERTIES
        echo "GOOGLE_MAPS_API_KEY=$GOOGLE_MAPS_API_KEY" >> $GRADLE_PROPERTIES
    fi
}
