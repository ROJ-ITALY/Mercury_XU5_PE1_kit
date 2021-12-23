inherit image-xlnx-xen

XEN_CMD_DEFAULT ?= "console=dtuart dtuart=serial0 dom0_mem=1024M dom0_max_vcpus=1 bootscrub=0 vwfi=native sched=null xen_colors=0-0 dom0_colors=1-9"
XEN_CMD_NO_SERIAL0 ?= "dom0_mem=1024M dom0_max_vcpus=1 bootscrub=0 vwfi=native sched=null xen_colors=0-0 dom0_colors=1-9"

XEN_CMD = "${@ '${XEN_CMD_NO_SERIAL0}' if d.getVar('XEN_DOMU_BAREMETAL') == '1' else '${XEN_CMD_DEFAULT}' }"

# Set device nodes in passthrough only for DomU baremetal demos. IT SEEMS NOT SUPPORTED IN OUR CURRENT VERSION!
#XEN_PASSTHROUGH_PATHS_DEMOS ?= "/amba/serial@ff000000 /amba/shm@0 /amba/ipi@0 /amba/timer@ff110000"
#XEN_PASSTHROUGH_PATHS = "${@ '${XEN_PASSTHROUGH_PATHS_DEMOS}' if d.getVar('XEN_DOMU_BAREMETAL') == '1' else '' }"

plnx_config_image_builder() {
	cp "${DEPLOY_DIR_IMAGE}/${INITRAMFS_IMAGE}-${MACHINE}.cpio.gz" "${WORKDIR}/image_builder/rootfs.cpio.gz"
	cp "${DEPLOY_DIR_IMAGE}/${XEN}" "${WORKDIR}/image_builder/"
	cp "${DEPLOYDIR}/${DOM0_KERNEL}" "${WORKDIR}/image_builder/"
	cp "${DEPLOY_DIR_IMAGE}/${DEVICE_TREE}" "${WORKDIR}/image_builder/"
	cat > ${XEN_CONFIG} << EOL
MEMORY_START=${MEMORY_START}
MEMORY_END=${MEMORY_END}
DEVICE_TREE=${DEVICE_TREE}
XEN=${XEN}
XEN_CMD="${XEN_CMD}"
DOM0_KERNEL=${DOM0_KERNEL}
DOM0_RAMDISK=${DOM0_RAMDISK}
NUM_DOMUS=${NUM_DOMUS}
UBOOT_SOURCE=${UBOOT_SOURCE}
UBOOT_SCRIPT=${UBOOT_SCRIPT}
EOL
}

plnx_compile_image_builder() {
	if [ "${XEN_CONFIG_SKIP}" != "1" ];then
		plnx_config_image_builder
	fi
	if [ -f "${XEN_CONFIG}" ]; then
                ${DEPLOY_DIR_IMAGE}/uboot-script-gen -c ${XEN_CONFIG} -t "load mmc 1:1" -d $(dirname "${XEN_CONFIG}") -o ${PLNX_DEPLOY_DIR}/boot
        fi
}
