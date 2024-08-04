SUMMARY = "Open Source Mali Midgard GPU Kernel Drivers"
LICENSE = "GPL-2.0-only"
LIC_FILES_CHKSUM = "file://driver/product/kernel/license.txt;md5=13e14ae1bd7ad5bff731bba4a31bb510"

inherit module

EXTRA_OEMAKE = ""

S = "${UNPACKDIR}/git"

do_compile() {
	oe_runmake -C ${STAGING_KERNEL_DIR} \
		M=$(pwd)/driver/product/kernel/drivers/gpu/arm/midgard \
		O=${STAGING_KERNEL_BUILDDIR} \
		EXTRA_CFLAGS="${MALI_FLAGS}" \
		${MALI_KCONFIG}
}

do_install() {
	oe_runmake -C ${STAGING_KERNEL_DIR} \
		M=$(pwd)/driver/product/kernel/drivers/gpu/arm/midgard \
		INSTALL_MOD_PATH="${D}" \
		O=${STAGING_KERNEL_BUILDDIR} \
		EXTRA_CFLAGS="${MALI_FLAGS}" \
		${MALI_KCONFIG} \
		modules_install
}

COMPATIBLE_MACHINE = "odroid-xu3|odroid-xu3-lite|odroid-xu4"
