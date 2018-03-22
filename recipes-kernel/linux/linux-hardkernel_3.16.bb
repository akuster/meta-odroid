FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}-${PV}:"

LINUX_VERSION ?= "3.16.55"
KBRANCH ?= "odroidc2-v3.16.y"
SRCREV ?= "79b46184d9952ea2a220913f3c5a3798eab573a9"
KBUILD_DEFCONFIG_odroid-c2 = "odroidc2_defconfig"

require linux-stable.inc
SRC_URI_odroid-c2 = "\
    git://github.com/hardkernel/linux;branch=${KBRANCH} \
    https://releases.linaro.org/components/toolchain/binaries/5.5-2017.10/aarch64-linux-gnu/gcc-linaro-${LINAROTOOLCHAIN}-x86_64_aarch64-linux-gnu.tar.xz;name=aarch64toolchain \
    file://0001-net-stmmac-create-one-debugfs-dir-per-net-device.patch \
"
SRC_URI[aarch64toolchain.md5sum] = "641f6a790c26bda22c0aaa6fb44f8775"
SRC_URI[aarch64toolchain.sha256sum] = "fa8c3a400f281fb7a62db267fd51f0a02fb43aa8b91701192a7ce040573866fb"

EXTRA_OEMAKE_odroid-c2 = 'V=1 CROSS_COMPILE=${TOOLCHAIN_PREFIX} HOSTCC="${BUILD_CC} ${BUILD_CFLAGS} ${BUILD_LDFLAGS}"'

LINAROTOOLCHAIN = "5.5.0-2017.10"
TOOLCHAIN_PREFIX_odroid-c2 = "aarch64-linux-gnu-"
HOST_PREFIX_odroid-c2 = "${TOOLCHAIN_PREFIX}"
PATH_prepend ="${WORKDIR}/gcc-linaro-${LINAROTOOLCHAIN}-x86_64_aarch64-linux-gnu/bin:"

# Enable 3.5' TFT Screen
#KERNEL_MODULE_AUTOLOAD_append_odroid-c2 = " fbtft_device flexfb sx865x aml_i2c pwm-meson pwm-ctrl "
#module_conf_fbtft_device = "options fbtft_device name=flexpfb rotate=90"
#module_conf_flexfb = "options flexfb chip=ili9488"
#KERNEL_MODULE_PROBECONF += "fbtft_device flexfb"
