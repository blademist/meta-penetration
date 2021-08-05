SUMMARY = "Extra tools"
LICENSE = "MIT"

inherit packagegroup

PROVIDES = "${PACKAGES}"

PACKAGES = "\
    packagegroup-extra-tools \
    packagegroup-extra-netapps \
    "

RDEPENDS:packagegroup-extra-tools = "\
    packagegroup-extra-netapps \
    "

WEBBROWSER ?= "epiphany"
SUMMARY:packagegroup-extra-netapps = "Extra network applications"
RDEPENDS:packagegroup-extra-netapps = "\
    ${WEBBROWSER} \
    "
