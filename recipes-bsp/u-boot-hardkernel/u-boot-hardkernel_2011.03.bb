require u-boot-hardkernel.inc
LICENSE="GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=1707d6db1d42237583f50183a5651ecb"

UBOOT_REPO_URI ?= "git://github.com/hardkernel/u-boot.git"
UBOOT_BRANCH ?= "odroidc-v2011.03"

SRCREV = "73843c840cc224228d0982bd59e8e5e98b5d465b"

PV = "v2011.03+git${SRCPV}"

SRC_URI = " \
    ${UBOOT_REPO_URI};branch=${UBOOT_BRANCH} \
    file://0001-ucl-use-host-compiler-supplied-by-OE.patch \
    file://0001-compiler_gcc-do-not-redefine-__gnu_attributes.patch \
    file://0001-ARM-asm-io.h-use-static-inline.patch \
    file://0001-board.c-fix-inline-issue.patch \
    file://0001-compile-add-gcc5.patch \
    file://0001-main-fix-inline-issue.patch \
    file://0001-usb-use-define-not-func.patch \
    file://0002-fix-build-error-under-gcc6.patch \
    file://m8_osd_hw_build_fix.patch \
    file://0001-gcc-7-fix.patch \
"

SRC_URI_append = " ${@bb.utils.contains('TUNE_FEATURES','callconvention-hard',' file://0002-added-hardfp-support.patch ','',d)}"

PARALLEL_MAKE = ""

do_compile_append () {
    # Move result to usual location
    mv ${B}/sd_fuse/${UBOOT_BINARY} ${B}
}

BL1_SUFFIX ?= "bin.hardkernel"
BL1_IMAGE ?= "bl1-${MACHINE}-${PV}-${PR}.${BL1_SUFFIX}"
BL1_BINARY ?= "bl1.${BL1_SUFFIX}"
BL1_SYMLINK ?= "bl1-${MACHINE}.${BL1_SUFFIX}"

do_deploy_append () {
    install ${S}/sd_fuse/${BL1_BINARY} ${DEPLOYDIR}/${BL1_IMAGE}

    cd ${DEPLOYDIR}
    rm -f ${BL1_BINARY} ${BL1_SYMLINK}
    ln -sf ${BL1_IMAGE} ${BL1_SYMLINK}
    ln -sf ${BL1_IMAGE} ${BL1_BINARY}
}

PACKAGE_ARCH = "${MACHINE_ARCH}"

PROVIDES =+ "u-boot"

COMPATIBLE_MACHINE = "(odroid-c1)"
