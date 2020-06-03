SUMMARY = "Extra tools"
LICENSE = "MIT"

inherit packagegroup

PROVIDES = "${PACKAGES}"

PACKAGES = "\
    packagegroup-extra-tools \
    packagegroup-extra-netapps \
    "

RDEPENDS_packagegroup-extra-tools = "\
    packagegroup-extra-netapps \
    "

WEBBROWSER ?= "epiphany"
SUMMARY_packagegroup-extra-netapps = "Extra network applications"
RDEPENDS_packagegroup-extra-netapps = "\
    ${WEBBROWSER} \
    "
