SUMMARY = "Arm Trusted Firmware"
LICENSE = "BSD-3-Clause"
LIC_FILES_CHKSUM = "file://license.md;md5=829bdeb34c1d9044f393d5a16c068371"

SRCREV = "d0c104e1e1ad0102f0f4c70997b7ee6e6fbbe273"
SRC_URI = "git://github.com/ARM-software/arm-trusted-firmware.git;branch=master;protocol=https"
SRC_URI += "file://add_odroid_c2.patch"

export CROSS_COMPILE = "${TARGET_PREFIX}"

CFLAGS[unexport] = "1"
LDFLAGS[unexport] = "1"
AS[unexport] = "1"
LD[unexport] = "1"

inherit native

PV = "1.2+git${SRCPV}"

do_configure[noexec] = "1"

do_compile () {
	oe_runmake -C ${S}/tools/fip_create/
}

do_install () {
	install -d ${D}/${sbindir}
	install -m 775 ${S}/tools/fip_create/fip_create ${D}/${sbindir}
}
