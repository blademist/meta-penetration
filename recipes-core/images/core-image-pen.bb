DESCRIPTION = "Image with penetration tools."

IMAGE_FEATURES += "splash package-management x11-base x11-sato ssh-server-openssh hwcodecs debug-tweaks"

LICENSE = "MIT"

inherit core-image

IMAGE_INSTALL += "\
    packagegroup-penetration-tools \
    "

inherit features_check
REQUIRED_DISTRO_FEATURES = "wifi"

