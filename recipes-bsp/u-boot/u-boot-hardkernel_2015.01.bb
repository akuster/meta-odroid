require recipes-bsp/u-boot/u-boot.inc
DESCRIPTION = "Odroid C2 boot loader supported by the hardkernel product"
SECTION = "bootloaders"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/GPL-2.0;md5=801f80980d171dd6425610833a22dbe6"

FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

PROVIDES += "virtual/bootloader u-boot"

LIC_FILES_CHKSUM = "file://Licenses/gpl-2.0.txt;md5=b234ee4d69f5fce4486a80fdaf4a4263"

USE_BOOTSCR = "0"

# from where to fetch the u-boot
UBOOT_REPO_URI ??= "git://github.com/hardkernel/u-boot.git"
BRANCH = "odroidc2-v2015.01"
SRC_URI = "${UBOOT_REPO_URI};branch=${BRANCH} \
           file://odroid-c2-hardkernel/boot.ini \
           https://releases.linaro.org/components/toolchain/binaries/4.9-2017.01/aarch64-linux-gnu/gcc-linaro-${LINAROTOOLCHAIN}-x86_64_aarch64-linux-gnu.tar.xz;name=aarch64toolchain;subdir=git \
 "
SRC_URI[aarch64toolchain.md5sum] = "631c4c7b1fe9cb115cf51bd6a926acb7"
SRC_URI[aarch64toolchain.sha256sum] = "d1f2761b697e6b49f5db1ec0cd48d2fd98224be8cb5ef182093f691e99c923eb"

# TAG s905_6.0.1_v3.7
SRCREV = "95264d19d04930f67f10f162df70de447659329d"

PR = "${PV}+git${SRCPV}"

UBOOT_SUFFIX ?= "bin"

PACKAGE_ARCH = "${MACHINE_ARCH}"

S = "${WORKDIR}/git"

inherit uboot-boot-scr

DEPENDS += "bc-native atf-native"

EXTRA_OEMAKE_odroid-c2 = 'V=1 CROSS_COMPILE=${TOOLCHAIN_PREFIX} HOSTCC="${BUILD_CC} ${BUILD_CFLAGS} ${BUILD_LDFLAGS}"'
LINAROTOOLCHAIN = "4.9.4-2017.01"
TOOLCHAIN_PREFIX_odroid-c2 = "aarch64-linux-gnu-"
HOST_PREFIX_odroid-c2 = "${TOOLCHAIN_PREFIX}"
PATH_prepend ="${S}/gcc-linaro-${LINAROTOOLCHAIN}-x86_64_aarch64-linux-gnu/bin:"

do_configure_append() {
	cp ${WORKDIR}/odroid-c2-hardkernel/boot.ini ${B}/
}

do_compile_append_odroid-c2 () {
	cp ${S}/sd_fuse/u-boot.bin ${B}/${UBOOT_BINARY}
}

COMPATIBLE_MACHINE = "(odroid-c2)"
UBOOT_MACHINE_odroid-c2 = "odroidc2_config"
