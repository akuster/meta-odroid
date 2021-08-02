FILESEXTRAPATHS:prepend := "${THISDIR}/${PN}:"

SRC_URI:append:odroid-c2 = " file://fw_env.config"

do_install:append:odroid-c2() {
	rm -rf ${D}${sysconfdir}/fw_env.config
	install -m 0644 ${WORKDIR}/fw_env.config ${D}${sysconfdir}/fw_env.config
}
