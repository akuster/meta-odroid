require kernel-module-mali-midgard.inc

BRANCH="TX011-SW-99002-r26p0-01rel0"
SRC_URI = "git://github.com/akuster/mali-kernel-modules.git;branch=${BRANCH}"

SRCREV = "d8ca6433357b4bde4b12cc7715ac811c2c92d4e1"

python __anonymous() {
    platform = d.getVar('MALI_DRIVER_PLATFORM', True)
    if not platform:
        platform = "devicetree"

    config = ["CONFIG_MALI_MIDGARD=m",
              "CONFIG_MALI_GATOR_SUPPORT=y",
              "CONFIG_MALI_EXPERT=y",
              "CONFIG_MALI_PLATFORM_FAKE=y",
              "CONFIG_MALI_PLATFORM_THIRDPARTY=y",
              "CONFIG_MALI_PLATFORM_THIRDPARTY_NAME=" + platform]

    if platform == "5422":
        d.appendVar('SRC_URI', ' ' + d.getVar('5422_PATCH', True))

    for c in config:
        d.appendVar('MALI_FLAGS', '-D' + c + ' ')
        d.appendVar('MALI_KCONFIG', c + ' ')
}
export INSTALL_MOD_DIR="kernel/mali"
