# SPDX-FileCopyrightText: Huawei Inc.
#
# SPDX-License-Identifier: Apache-2.0

LICENSE = "BSD-3-Clause"
LIC_FILES_CHKSUM = "file://LICENSE;md5=cbbd27594afd089daa160d3a16dd515a \
                    file://googlemock/LICENSE;md5=cbbd27594afd089daa160d3a16dd515a \
                    file://googlemock/scripts/generator/LICENSE;md5=2c0b90db7465231447cf2dd2e8163333 \
                    file://googletest/LICENSE;md5=cbbd27594afd089daa160d3a16dd515a"

SRC_URI = "git://git.ostc-eu.org/OSTC/OHOS/components/staging/third_party_googletest.git;protocol=https;branch=ostc"
SRCREV = "ea0d703c5c295efc7ab524f09533dafdc2b13fe9"
S = "${WORKDIR}/git"
PV = "0.0+git${SRCPV}"

# The OHOS fork of googletest is not versioned properly.
# Move the naked .so files to the primary package.
SOLIBS = ".so"
FILES_SOLIBSDEV = ""

inherit zmk

do_configure_prepend() {
    # Remove upstream autotools. Those are unmaintained in the OHOS fork.
    # They cause bitbake to be confused and run the wrong build system.
    rm -f ${S}/Makefile.am ${S}/configure.ac ${S}/configure
}
