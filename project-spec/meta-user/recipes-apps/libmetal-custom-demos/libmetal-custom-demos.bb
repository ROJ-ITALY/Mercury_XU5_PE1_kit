#
# This file is the libmetal-custom-demos recipe.
#

SUMMARY = "libmetal-custom-demos built via Vitis IDE"
SECTION = "PETALINUX/apps"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

RDEPENDS_${PN} = "libmetal"

SRC_URI = " file://libmetal_amp_demo_ipi_server.elf \
	   		file://libmetal_amp_demo_shmem_server.elf \
			file://libmetal_amp_demo_throughput_server.elf \
			file://libmetal_amp_demo_ipi_client.bin \
			file://libmetal_amp_demo_shmem_client.bin \
			file://libmetal_amp_demo_throughput_client.bin \
			file://libmetal_amp_demo_ipi_linux_client.elf \
			file://libmetal_amp_demo_shmem_linux_client.elf \
			file://libmetal_amp_demo_throughput_linux_client.elf \
			file://libmetal_amp_demo_client.cfg \
		  "
S = "${WORKDIR}"
INSANE_SKIP_${PN} = "arch"

do_install() {
		# Install firmware into /lib/firmware on target
		install -d ${D}/lib/firmware
		install -m 0644 ${S}/libmetal_amp_demo_ipi_server.elf ${D}${base_libdir}/firmware/libmetal_amp_demo_ipi_server.elf
		install -m 0644 ${S}/libmetal_amp_demo_shmem_server.elf ${D}${base_libdir}/firmware/libmetal_amp_demo_shmem_server.elf
		install -m 0644 ${S}/libmetal_amp_demo_throughput_server.elf ${D}${base_libdir}/firmware/libmetal_amp_demo_throughput_server.elf

		if [ "${XEN_DOMU_BAREMETAL}" == "1" ]; then
			install -d ${D}/boot
			install -m 0644 ${S}/libmetal_amp_demo_ipi_client.bin ${D}/boot/libmetal_amp_demo_ipi_client.bin
			install -m 0644 ${S}/libmetal_amp_demo_shmem_client.bin ${D}/boot/libmetal_amp_demo_shmem_client.bin
			install -m 0644 ${S}/libmetal_amp_demo_throughput_client.bin ${D}/boot/libmetal_amp_demo_throughput_client.bin
			install -d ${D}${sysconfdir}/xen
			install -m 0644 ${S}/libmetal_amp_demo_client.cfg ${D}${sysconfdir}/xen/libmetal_amp_demo_client.cfg
		else
			install -d ${D}${bindir}
			install -m 0755 ${S}/libmetal_amp_demo_ipi_linux_client.elf ${D}${bindir}/libmetal_amp_demo_ipi_linux_client.elf
			install -m 0755 ${S}/libmetal_amp_demo_shmem_linux_client.elf ${D}${bindir}/libmetal_amp_demo_shmem_linux_client.elf
			install -m 0755 ${S}/libmetal_amp_demo_throughput_linux_client.elf ${D}${bindir}/libmetal_amp_demo_throughput_linux_client.elf
		fi
}

FILES_${PN} = "${base_libdir}/firmware/ /boot/ ${sysconfdir}/xen/ ${bindir} "
