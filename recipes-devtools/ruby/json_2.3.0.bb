SUMMARY = "An implementation of the JSON specification according to RFC 4627"
DESCRIPTION = "An implementation of the JSON specification according to RFC 4627"

LICENSE = "BSD-2-Clause"
LIC_FILES_CHKSUM = "file://LICENSE;md5=8a960b08d972f43f91ae84a6f00dcbfb"

SRC_URI = "git://github.com/flori/json.git"
SRCREV = "92cf5c451a6ec0f3a00e291eb909e57cf38fbea4"

inherit ruby

S = "${WORKDIR}/git"

BBCLASSEXTEND = "native"

SRCNAME = "json_pure"
