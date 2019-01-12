FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}-${PV}:"

LINUX_VERSION ?= "3.16.61"
KBRANCH ?= "odroidc2-v3.16.y"
SRCREV ?= "e1d2630687c4eab7a0fdb3f260caa0c64b3a9f36"
KBUILD_DEFCONFIG_odroid-c2 = "odroidc2_defconfig"

require linux-stable.inc
SRC_URI = "\
    git://github.com/hardkernel/linux;branch=${KBRANCH} \
"
# Enable 3.5' TFT Screen
#KERNEL_MODULE_AUTOLOAD_append_odroid-c2 = " fbtft_device flexfb sx865x aml_i2c pwm-meson pwm-ctrl "
#module_conf_fbtft_device = "options fbtft_device name=flexpfb rotate=90"
#module_conf_flexfb = "options flexfb chip=ili9488"
#KERNEL_MODULE_PROBECONF += "fbtft_device flexfb"
COMPATIBLE_MACHINE = "(odroid-c2|odroid-c2-hardkernel)"
