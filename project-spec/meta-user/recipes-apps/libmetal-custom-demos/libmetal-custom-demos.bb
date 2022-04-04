#
# This file is the libmetal-custom-demos recipe.
# The bare-metal demo works using TTC0 to measure latency.
# The FreeRTOS demo works using TTC1 to measure latency instead of TTC0.

SUMMARY = "libmetal-custom-demos built via Vitis IDE"
SECTION = "PETALINUX/apps"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

RDEPENDS_${PN} = "libmetal"

SRC_URI = " file://libmetal_amp_demo_ipi_server.elf \
	   		file://libmetal_amp_demo_shmem_server.elf \
			file://libmetal_amp_demo_throughput_server.elf \
			\
			file://libmetal_amp_demo_rtos_ipi_server.elf \
	   		file://libmetal_amp_demo_rtos_shmem_server.elf \
			file://libmetal_amp_demo_rtos_throughput_server.elf \
			\
			file://libmetal_amp_demo_ipi_client.bin \
			file://libmetal_amp_demo_shmem_client.bin \
			file://libmetal_amp_demo_throughput_client.bin \
			file://libmetal_amp_demo_client.cfg \
			\
			file://libmetal_amp_demo_linux_ipi_client.elf \
			file://libmetal_amp_demo_linux_shmem_client.elf \
			file://libmetal_amp_demo_linux_throughput_client.elf \
			\
			file://libmetal_amp_demo_rtos_ipi_client.bin \
			file://libmetal_amp_demo_rtos_shmem_client.bin \
			file://libmetal_amp_demo_rtos_throughput_client.bin \
			file://libmetal_amp_demo_rtos_client.cfg \
			\
			file://xemacps_example_intr_dma_1.bin \
			file://test_xen_eth1_dma.cfg \
		  "

S = "${WORKDIR}"
INSANE_SKIP_${PN} = "arch"

do_install() {
		# Install firmware server (bare-metal and FreeRTOS) into /lib/firmware on target
		install -d ${D}/lib/firmware
		# bare-metal server
		install -m 0644 ${S}/libmetal_amp_demo_ipi_server.elf ${D}${base_libdir}/firmware/libmetal_amp_demo_ipi_server.elf
		install -m 0644 ${S}/libmetal_amp_demo_shmem_server.elf ${D}${base_libdir}/firmware/libmetal_amp_demo_shmem_server.elf
		install -m 0644 ${S}/libmetal_amp_demo_throughput_server.elf ${D}${base_libdir}/firmware/libmetal_amp_demo_throughput_server.elf
		# FreeRTOS server
		install -m 0644 ${S}/libmetal_amp_demo_rtos_ipi_server.elf ${D}${base_libdir}/firmware/libmetal_amp_demo_rtos_ipi_server.elf
		install -m 0644 ${S}/libmetal_amp_demo_rtos_shmem_server.elf ${D}${base_libdir}/firmware/libmetal_amp_demo_rtos_shmem_server.elf
		install -m 0644 ${S}/libmetal_amp_demo_rtos_throughput_server.elf ${D}${base_libdir}/firmware/libmetal_amp_demo_rtos_throughput_server.elf

		if [ "${XEN_DOMU_BAREMETAL}" == "1" ]; then
			install -d ${D}/boot
			# bare-metal client
			install -m 0644 ${S}/libmetal_amp_demo_ipi_client.bin ${D}/boot/libmetal_amp_demo_ipi_client.bin
			install -m 0644 ${S}/libmetal_amp_demo_shmem_client.bin ${D}/boot/libmetal_amp_demo_shmem_client.bin
			install -m 0644 ${S}/libmetal_amp_demo_throughput_client.bin ${D}/boot/libmetal_amp_demo_throughput_client.bin

			install -m 0644 ${S}/xemacps_example_intr_dma_1.bin ${D}/boot/xemacps_example_intr_dma_1.bin		

			# FreeRTOS client
			install -m 0644 ${S}/libmetal_amp_demo_rtos_ipi_client.bin ${D}/boot/libmetal_amp_demo_rtos_ipi_client.bin
			install -m 0644 ${S}/libmetal_amp_demo_rtos_shmem_client.bin ${D}/boot/libmetal_amp_demo_rtos_shmem_client.bin
			install -m 0644 ${S}/libmetal_amp_demo_rtos_throughput_client.bin ${D}/boot/libmetal_amp_demo_rtos_throughput_client.bin

			install -d ${D}${sysconfdir}/xen
			install -m 0644 ${S}/libmetal_amp_demo_client.cfg ${D}${sysconfdir}/xen/libmetal_amp_demo_client.cfg
			install -m 0644 ${S}/libmetal_amp_demo_rtos_client.cfg ${D}${sysconfdir}/xen/libmetal_amp_demo_rtos_client.cfg

			install -m 0644 ${S}/test_xen_eth1_dma.cfg ${D}${sysconfdir}/xen/test_xen_eth1_dma.cfg

		else
			install -d ${D}${bindir}
			install -m 0755 ${S}/libmetal_amp_demo_linux_ipi_client.elf ${D}${bindir}/libmetal_amp_demo_linux_ipi_client.elf
			install -m 0755 ${S}/libmetal_amp_demo_linux_shmem_client.elf ${D}${bindir}/libmetal_amp_demo_linux_shmem_client.elf
			install -m 0755 ${S}/libmetal_amp_demo_linux_throughput_client.elf ${D}${bindir}/libmetal_amp_demo_linux_throughput_client.elf
		fi
}

FILES_${PN} = "${base_libdir}/firmware/ /boot/ ${sysconfdir}/xen/ ${bindir} "
