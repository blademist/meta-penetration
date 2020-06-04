DESCRIPTION = "XFCE image with penetration tools."

IMAGE_FEATURES += "package-management ssh-server-openssh hwcodecs debug-tweaks"

LICENSE = "MIT"

IMAGE_INSTALL += "\
    packagegroup-core-boot \
    packagegroup-core-x11 \
    packagegroup-xfce-base \
    kernel-modules \
    packagegroup-core-tools \
    packagegroup-penetration-tools \
    packagegroup-extra-tools \
"
inherit features_check
REQUIRED_DISTRO_FEATURES = "x11 wifi"

IMAGE_LINGUAS ?= " "

export IMAGE_BASENAME = "core-image-pen-xfce"

inherit core-image
