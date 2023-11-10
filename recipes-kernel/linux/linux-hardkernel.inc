DESCRIPTION = "HardKernel Kernel"
SECTION = "kernel"
LICENSE = "GPL-2.0-only"

LIC_FILES_CHKSUM = "file://COPYING;md5=d7810fab7487fb0aad327b76f1be7cd7"

DEPENDS = "lzop-native"

SRC_URI = "git://github.com/hardkernel/linux.git;branch=${KBRANCH};protocol=https"

inherit kernel
require recipes-kernel/linux/linux-yocto.inc


PV = "${LINUX_VERSION}+git"

KCONF_BSP_AUDIT_LEVEL = "0"

COMPATIBLE_MACHINE = "(odroid-c1|odroid-c2|odroid-xu3|odroid-xu4|odroid-xu3-lite|odroid-hc1)"
