package com.lachesis.mybatis.plugin;

import java.io.File;
import java.util.ArrayList;
import java.util.Dictionary;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.mybatis.generator.api.FullyQualifiedTable;
import org.mybatis.generator.api.GeneratedJavaFile;
import org.mybatis.generator.api.GeneratedXmlFile;
import org.mybatis.generator.api.IntrospectedColumn;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.JavaFormatter;
import org.mybatis.generator.api.PluginAdapter;
import org.mybatis.generator.api.ShellCallback;
import org.mybatis.generator.api.dom.DefaultJavaFormatter;
import org.mybatis.generator.api.dom.DefaultXmlFormatter;
import org.mybatis.generator.api.dom.java.CompilationUnit;
import org.mybatis.generator.api.dom.java.FullyQualifiedJavaType;
import org.mybatis.generator.api.dom.java.Interface;
import org.mybatis.generator.api.dom.java.JavaVisibility;
import org.mybatis.generator.api.dom.java.TopLevelClass;
import org.mybatis.generator.api.dom.xml.Attribute;
import org.mybatis.generator.api.dom.xml.Document;
import org.mybatis.generator.api.dom.xml.TextElement;
import org.mybatis.generator.api.dom.xml.XmlElement;
import org.mybatis.generator.codegen.XmlConstants;
import org.mybatis.generator.codegen.ibatis2.sqlmap.elements.AbstractXmlElementGenerator;
import org.mybatis.generator.codegen.mybatis3.MyBatis3FormattingUtilities;
import org.mybatis.generator.config.JavaClientGeneratorConfiguration;
import org.mybatis.generator.config.JavaModelGeneratorConfiguration;
import org.mybatis.generator.config.SqlMapGeneratorConfiguration;
import org.mybatis.generator.exception.ShellException;
import org.mybatis.generator.internal.DefaultShellCallback;

public class FileGeneratorPlugin extends PluginAdapter {

	private Map<FullyQualifiedTable, List<XmlElement>> elementsToAdd;
	private ShellCallback shellCallback = null;


	public FileGeneratorPlugin() {

		super();
		elementsToAdd = new HashMap<FullyQualifiedTable, List<XmlElement>>();		
		shellCallback = new DefaultShellCallback(false);
	}


	@Override
	public boolean validate(List<String> arg0) {
		return true;
	}

	@Override
	public List<GeneratedJavaFile> contextGenerateAdditionalJavaFiles(IntrospectedTable introspectedTable) {

		List<GeneratedJavaFile> superGenerateList = new ArrayList<GeneratedJavaFile>();

		
		contextGenerateDaoFiles(introspectedTable, superGenerateList);
		contextGenerateDbModelAdditionalFiles(introspectedTable, superGenerateList);
		contextGenerateDaoAdditionalFiles(introspectedTable, superGenerateList);

		return superGenerateList;
	}


	private void contextGenerateDaoFiles(IntrospectedTable introspectedTable,
			List<GeneratedJavaFile> superGenerateList) {
		Dictionary<String, FullyQualifiedJavaType> dbTypeDictionary = new Hashtable<String, FullyQualifiedJavaType>();
		JavaClientGeneratorConfiguration javaClientConfig = context.getJavaClientGeneratorConfiguration();		

		for (GeneratedJavaFile javaFile : introspectedTable.getGeneratedJavaFiles()) {

			CompilationUnit unit = javaFile.getCompilationUnit();
			FullyQualifiedJavaType baseModelJavaType = unit.getType();
			String shortName = baseModelJavaType.getShortName();
			if (!shortName.endsWith("Mapper")) {
				dbTypeDictionary.put(shortName, baseModelJavaType);
			}
		}



		JavaFormatter javaFormatter = context.getJavaFormatter();
		for (GeneratedJavaFile javaFile : introspectedTable.getGeneratedJavaFiles()) {


			CompilationUnit unit = javaFile.getCompilationUnit();
			FullyQualifiedJavaType baseModelJavaType = unit.getType();
			String shortName = baseModelJavaType.getShortName();


			if (shortName.endsWith("Mapper")) {                

				if(introspectedTable.getPrimaryKeyColumns().size() != 1)
					continue;


				Interface mapperInterface = new Interface(javaClientConfig.getTargetPackage() + "." + shortName);

				mapperInterface.setVisibility(JavaVisibility.PUBLIC);
				mapperInterface.addJavaDocLine("/**");
				mapperInterface.addJavaDocLine(" * This file is auto-generate by Mybatis Generator.  DO NOT Modify this File!!!!!");
				mapperInterface.addJavaDocLine(" */");

				FullyQualifiedJavaType daoSuperType = new FullyQualifiedJavaType(properties.getProperty("rootDaoInterface"));


				// 添加泛型支持
				FullyQualifiedJavaType dbModelType = dbTypeDictionary.get(shortName.substring(0, shortName.length() - ("Mapper".length())));;

				daoSuperType.addTypeArgument(introspectedTable.getPrimaryKeyColumns().get(0).getFullyQualifiedJavaType());
				daoSuperType.addTypeArgument(dbModelType);
				mapperInterface.addImportedType(dbModelType);
				mapperInterface.addImportedType(daoSuperType);
				mapperInterface.addSuperInterface(daoSuperType);

				GeneratedJavaFile mapperJavafile = new GeneratedJavaFile(mapperInterface, javaClientConfig.getTargetProject(), javaFormatter);
				System.out.println("mapperJavafile:" + mapperJavafile.toString());                
				superGenerateList.add(mapperJavafile);

			}
		}
	}

	private void contextGenerateDaoAdditionalFiles(IntrospectedTable introspectedTable,
			List<GeneratedJavaFile> superGenerateList) {

		String objectName = introspectedTable.getTableConfiguration().getDomainObjectName();
		JavaFormatter format = new DefaultJavaFormatter();
		format.setContext(context);
		
		//DAO
		JavaClientGeneratorConfiguration javaClientConfig = context.getJavaClientGeneratorConfiguration();
		Interface clientClass = new Interface(javaClientConfig.getTargetPackage() + "." + objectName + "MapperExt");
		clientClass.setVisibility(JavaVisibility.PUBLIC);

		/*继承ISearchableDAO*/
		FullyQualifiedJavaType extParentClass = new FullyQualifiedJavaType(
				"com.lachesis.windranger.common.model.ISearchableDAO");
		clientClass.addImportedType(extParentClass);
		clientClass.addSuperInterface(extParentClass);

		GeneratedJavaFile dbModelFile = new GeneratedJavaFile(clientClass, javaClientConfig.getTargetProject(), format);
		try {

			File mapperDir = this.shellCallback.getDirectory(javaClientConfig.getTargetProject(), javaClientConfig.getTargetPackage());
			File mapperFile = new File(mapperDir, dbModelFile.getFileName());

			if (!mapperFile.exists()) {
				System.out.println("dbModelFile:" + dbModelFile.toString());
				superGenerateList.add(dbModelFile);
			}

		} catch (ShellException e) {
			e.printStackTrace();
		}
	}

	private void contextGenerateDbModelAdditionalFiles(IntrospectedTable introspectedTable,
			List<GeneratedJavaFile> superGenerateList) {
		
		
		String objectName = introspectedTable.getTableConfiguration().getDomainObjectName();
		JavaFormatter format = new DefaultJavaFormatter();
		format.setContext(context);
		
		//DB Model
		JavaModelGeneratorConfiguration javaModelConfig = context.getJavaModelGeneratorConfiguration();
		FullyQualifiedJavaType parentClass = new FullyQualifiedJavaType(javaModelConfig.getTargetPackage() + "." + objectName);
		TopLevelClass topLevelClass = new TopLevelClass(javaModelConfig.getTargetPackage() + "." + objectName + "Ext");
		topLevelClass.addImportedType(parentClass);
		topLevelClass.setSuperClass(parentClass);
		topLevelClass.setVisibility(JavaVisibility.PUBLIC);
		GeneratedJavaFile dbModelFile = new GeneratedJavaFile(topLevelClass, javaModelConfig.getTargetProject(), format);
		try {

			File mapperDir = this.shellCallback.getDirectory(javaModelConfig.getTargetProject(), javaModelConfig.getTargetPackage());
			File mapperFile = new File(mapperDir, dbModelFile.getFileName());

			if (!mapperFile.exists()) {
				System.out.println("mapperFile:" + mapperFile.toString());
				superGenerateList.add(dbModelFile);
			}

		} catch (ShellException e) {
			e.printStackTrace();
		}
	}


	public boolean sqlMapInsertElementGenerated(XmlElement element, IntrospectedTable introspectedTable) {
		addSaveElement(element, introspectedTable.getFullyQualifiedTable());
		return true;
	}

	public boolean sqlMapDeleteByPrimaryKeyElementGenerated(XmlElement element, IntrospectedTable introspectedTable) {
		addDeleteElement(element, introspectedTable);
		addDeleteByBean(element, introspectedTable);
		return true;
	}

	public boolean sqlMapSelectByPrimaryKeyElementGenerated(XmlElement element, IntrospectedTable introspectedTable) {
		addSelectByBean(element, introspectedTable);
		addCountByBean(element, introspectedTable);
		return true;
	}

	public boolean sqlMapUpdateByPrimaryKeyWithoutBLOBsElementGenerated(XmlElement element,
			IntrospectedTable introspectedTable) {
		addUpdateByPrimaryKeySelective(element, introspectedTable);
		return true;
	}

	public boolean sqlMapUpdateByPrimaryKeySelectiveElementGenerated(
			XmlElement element, IntrospectedTable introspectedTable) {
		return true;
	}

	private void addUpdateByPrimaryKeySelective(XmlElement element, IntrospectedTable introspectedTable) {

		FullyQualifiedTable fqt = introspectedTable.getFullyQualifiedTable();
		// 添加selectByBean节点
		XmlElement answer = new XmlElement("update");
		answer.addAttribute(new Attribute("id", "updateByPrimaryKeySelective"));

		String objectName = introspectedTable.getTableConfiguration().getDomainObjectName();
		JavaModelGeneratorConfiguration config = context.getJavaModelGeneratorConfiguration();
		answer.addAttribute(new Attribute("parameterType", config.getTargetPackage() + "." + objectName));

		StringBuilder sb = new StringBuilder();
		sb.append("update "); //$NON-NLS-1$
		sb.append(introspectedTable.getAliasedFullyQualifiedTableNameAtRuntime());

		sb.append(" set"); //$NON-NLS-1$

		answer.addElement(new TextElement(sb.toString()));
		Iterator<IntrospectedColumn> iter = introspectedTable.getNonBLOBColumns().iterator();
		
		XmlElement trimElement = new XmlElement("trim");
		trimElement.addAttribute(new Attribute("suffixOverrides", ","));
		answer.addElement(trimElement);
		
		while (iter.hasNext()) {
			IntrospectedColumn introspectedColumn = iter.next();
			if(MyBatis3FormattingUtilities.getAliasedEscapedColumnName(introspectedColumn).equals(introspectedTable.getPrimaryKeyColumns().get(0).getActualColumnName()))
				continue;
			XmlElement ifElement = new XmlElement("if");
			ifElement.addAttribute(new Attribute("test", introspectedColumn.getJavaProperty() + " != null"));

			TextElement tl = null;
			if (iter.hasNext()) {
				tl = new TextElement(MyBatis3FormattingUtilities.getAliasedEscapedColumnName(introspectedColumn) + " = "
						+ MyBatis3FormattingUtilities.getParameterClause(introspectedColumn, "") + ",");
			} else {
				tl = new TextElement(MyBatis3FormattingUtilities.getAliasedEscapedColumnName(introspectedColumn) + " = "
						+ MyBatis3FormattingUtilities.getParameterClause(introspectedColumn, ""));
			}

			ifElement.addElement(tl);

			trimElement.addElement(ifElement);

		}

		answer.addElement(new TextElement(" where "
				+ introspectedTable.getPrimaryKeyColumns().get(0).getActualColumnName() + " = "
				+ MyBatis3FormattingUtilities.getParameterClause(introspectedTable.getPrimaryKeyColumns().get(0), "")));
		;

		List<XmlElement> elements = elementsToAdd.get(fqt);
		if (elements == null) {
			elements = new ArrayList<XmlElement>();
			elementsToAdd.put(fqt, elements);
		}
		elements.add(answer);
	}

	private void addSelectByBean(XmlElement element, IntrospectedTable introspectedTable) {
		FullyQualifiedTable fqt = introspectedTable.getFullyQualifiedTable();

		// 添加selectByBean节点
		XmlElement answer = new XmlElement("select");
		answer.addAttribute(new Attribute("id", "selectByBean"));

		answer.addAttribute(new Attribute("resultMap", "BaseResultMap"));

		String objectName = introspectedTable.getTableConfiguration().getDomainObjectName();
		JavaModelGeneratorConfiguration config = context.getJavaModelGeneratorConfiguration();
		answer.addAttribute(new Attribute("parameterType", config.getTargetPackage() + "." + objectName));

		StringBuilder sb = new StringBuilder();
		sb.append("select * from "); //$NON-NLS-1$
		sb.append(introspectedTable.getAliasedFullyQualifiedTableNameAtRuntime());

		sb.append(" where 1=1 "); //$NON-NLS-1$

		answer.addElement(new TextElement(sb.toString()));

		Iterator<IntrospectedColumn> iter = introspectedTable.getNonBLOBColumns().iterator();
		while (iter.hasNext()) {
			IntrospectedColumn introspectedColumn = iter.next();
			XmlElement ifElement = new XmlElement("if");
			ifElement.addAttribute(new Attribute("test", introspectedColumn.getJavaProperty() + "!=null"));
			ifElement.addElement(
					new TextElement("and " + MyBatis3FormattingUtilities.getAliasedEscapedColumnName(introspectedColumn)
					+ " = " + MyBatis3FormattingUtilities.getParameterClause(introspectedColumn, "")));

			answer.addElement(ifElement);

			if (iter.hasNext()) {
				sb.append(',');
			}

		}

		List<XmlElement> elements = elementsToAdd.get(fqt);
		if (elements == null) {
			elements = new ArrayList<XmlElement>();
			elementsToAdd.put(fqt, elements);
		}
		elements.add(answer);
	}
	private void addCountByBean(XmlElement element, IntrospectedTable introspectedTable) {
		FullyQualifiedTable fqt = introspectedTable.getFullyQualifiedTable();

		// 添加selectByBean节点
		XmlElement answer = new XmlElement("select");
		answer.addAttribute(new Attribute("id", "countByBean"));

		answer.addAttribute(new Attribute("resultType", "int"));

		String objectName = introspectedTable.getTableConfiguration().getDomainObjectName();
		JavaModelGeneratorConfiguration config = context.getJavaModelGeneratorConfiguration();
		answer.addAttribute(new Attribute("parameterType", config.getTargetPackage() + "." + objectName));

		StringBuilder sb = new StringBuilder();
		sb.append("select count(0) from "); //$NON-NLS-1$
		sb.append(introspectedTable.getAliasedFullyQualifiedTableNameAtRuntime());

		sb.append(" where 1=1 "); //$NON-NLS-1$

		answer.addElement(new TextElement(sb.toString()));

		Iterator<IntrospectedColumn> iter = introspectedTable.getNonBLOBColumns().iterator();
		while (iter.hasNext()) {
			IntrospectedColumn introspectedColumn = iter.next();
			XmlElement ifElement = new XmlElement("if");
			ifElement.addAttribute(new Attribute("test", introspectedColumn.getJavaProperty() + "!=null"));
			ifElement.addElement(
					new TextElement("and " + MyBatis3FormattingUtilities.getAliasedEscapedColumnName(introspectedColumn)
					+ " = " + MyBatis3FormattingUtilities.getParameterClause(introspectedColumn, "")));

			answer.addElement(ifElement);

			if (iter.hasNext()) {
				sb.append(',');
			}

		}

		List<XmlElement> elements = elementsToAdd.get(fqt);
		if (elements == null) {
			elements = new ArrayList<XmlElement>();
			elementsToAdd.put(fqt, elements);
		}
		elements.add(answer);
	}

	private void addDeleteElement(XmlElement element, IntrospectedTable introspectedTable) {
		FullyQualifiedTable fqt=introspectedTable.getFullyQualifiedTable();
		XmlElement newElement = new XmlElement(element);

		// remove old id attribute and add a new one with the new name
		for (Iterator<Attribute> iterator = newElement.getAttributes().iterator(); iterator.hasNext();) {
			Attribute attribute = iterator.next();
			if ("id".equals(attribute.getName())) { //$NON-NLS-1$
				iterator.remove();
				Attribute idAttribute = new Attribute("id", "removeKeysWithSession"); //$NON-NLS-1$ //$NON-NLS-2$
				newElement.addAttribute(idAttribute);
				break;
			}
		}
		for (Iterator<Attribute> iterator = newElement.getAttributes().iterator(); iterator.hasNext();) {
			Attribute attribute = iterator.next();
			if ("parameterType".equals(attribute.getName())) {
				iterator.remove();
				Attribute typeAttribute = new Attribute("parameterType", "java.util.List"); //$NON-NLS-1$ //$NON-NLS-2$
				newElement.addAttribute(typeAttribute);
				break;
			}
		}

		newElement.getElements().clear();
		TextElement text = new TextElement("delete from " + fqt
				+ " where "+introspectedTable.getPrimaryKeyColumns().get(0).getActualColumnName()+" IN <foreach close=\")\" collection=\"list\" index=\"index\" item=\"item\" open=\"(\" separator=\",\"> #{item} </foreach>");
		newElement.getElements().add(text);
		// save the new element locally. We'll add it to the document
		// later
		List<XmlElement> elements = elementsToAdd.get(fqt);
		if (elements == null) {
			elements = new ArrayList<XmlElement>();
			elementsToAdd.put(fqt, elements);
		}
		elements.add(newElement);
	}

	private void addDeleteByBean(XmlElement element, IntrospectedTable introspectedTable) {
		FullyQualifiedTable fqt = introspectedTable.getFullyQualifiedTable();

		// 添加selectByBean节点
		XmlElement answer = new XmlElement("delete");
		answer.addAttribute(new Attribute("id", "deleteByBean"));		
		answer.addAttribute(new Attribute("parameterType", "java.lang.Long"));

		StringBuilder sb = new StringBuilder();
		sb.append("delete from "); //$NON-NLS-1$
		sb.append(introspectedTable.getAliasedFullyQualifiedTableNameAtRuntime());

		sb.append(" where 1=1 "); //$NON-NLS-1$

		answer.addElement(new TextElement(sb.toString()));

		Iterator<IntrospectedColumn> iter = introspectedTable.getNonBLOBColumns().iterator();
		while (iter.hasNext()) {
			IntrospectedColumn introspectedColumn = iter.next();
			XmlElement ifElement = new XmlElement("if");
			ifElement.addAttribute(new Attribute("test", introspectedColumn.getJavaProperty() + "!=null"));
			ifElement.addElement(
					new TextElement("and " + MyBatis3FormattingUtilities.getAliasedEscapedColumnName(introspectedColumn)
					+ " = " + MyBatis3FormattingUtilities.getParameterClause(introspectedColumn, "")));

			answer.addElement(ifElement);

			if (iter.hasNext()) {
				sb.append(',');
			}

		}

		List<XmlElement> elements = elementsToAdd.get(fqt);
		if (elements == null) {
			elements = new ArrayList<XmlElement>();
			elementsToAdd.put(fqt, elements);
		}
		elements.add(answer);
	}
	
	private void addSaveElement(XmlElement element, FullyQualifiedTable fqt) {
		XmlElement newElement = new XmlElement(element);

		// remove old id attribute and add a new one with the new name
		for (Iterator<Attribute> iterator = newElement.getAttributes().iterator(); iterator.hasNext();) {
			Attribute attribute = iterator.next();
			if ("id".equals(attribute.getName())) { //$NON-NLS-1$
				iterator.remove();
				Attribute idAttribute = new Attribute("id", "insertAndReturnKey"); //$NON-NLS-1$ //$NON-NLS-2$
				Attribute keyAttribute = new Attribute("keyProperty", "seqId");
				Attribute genAttribute = new Attribute("useGeneratedKeys", "true");

				newElement.addAttribute(idAttribute);
				newElement.addAttribute(keyAttribute);
				newElement.addAttribute(genAttribute);
				break;
			}
		}
		// save the new element locally. We'll add it to the document
		// later
		List<XmlElement> elements = elementsToAdd.get(fqt);
		if (elements == null) {
			elements = new ArrayList<XmlElement>();
			elementsToAdd.put(fqt, elements);
		}
		elements.add(newElement);
	}

	@Override
	public List<GeneratedXmlFile> contextGenerateAdditionalXmlFiles(IntrospectedTable introspectedTable) {

		Document document = new Document(XmlConstants.MYBATIS3_MAPPER_PUBLIC_ID,
				XmlConstants.MYBATIS3_MAPPER_SYSTEM_ID);
		document.setRootElement(getSqlMapElement(introspectedTable));


		List<GeneratedXmlFile> superGenerateList = new ArrayList<GeneratedXmlFile>();

		String objectName = introspectedTable.getTableConfiguration().getDomainObjectName();

		SqlMapGeneratorConfiguration sqlMapConfig = context.getSqlMapGeneratorConfiguration();
		DefaultXmlFormatter format = new DefaultXmlFormatter();
		format.setContext(context);
		GeneratedXmlFile xmlFile = new GeneratedXmlFile(document, objectName + "MapperExt.xml", sqlMapConfig.getTargetPackage(),
				sqlMapConfig.getTargetProject(), false, format);

		try {

			File mapperDir = this.shellCallback.getDirectory(sqlMapConfig.getTargetProject(), sqlMapConfig.getTargetPackage());
			File mapperFile = new File(mapperDir, xmlFile.getFileName());

			if (!mapperFile.exists()) {
				System.out.println("xmlFile:" + xmlFile.toString());
				superGenerateList.add(xmlFile);
			}

		} catch (ShellException e) {
			e.printStackTrace();
		}

		return superGenerateList;
	}

	protected XmlElement getSqlMapElement(IntrospectedTable introspectedTable) {
		String objectName = introspectedTable.getTableConfiguration().getDomainObjectName();
		JavaClientGeneratorConfiguration clientConfig = context.getJavaClientGeneratorConfiguration();
		XmlElement answer = new XmlElement("mapper"); //$NON-NLS-1$
		answer.addAttribute(new Attribute("namespace", //$NON-NLS-1$
				clientConfig.getTargetPackage() + "." + objectName + "MapperExt"));

		context.getCommentGenerator().addRootComment(answer);
		addBaseColumnListElement(answer, introspectedTable);
		return answer;
	}

	protected void addBaseColumnListElement(XmlElement parentElement, IntrospectedTable introspectedTable) {

		if (introspectedTable.getRules().generateBaseColumnList()) {

			AbstractXmlElementGenerator elementGenerator = new FileExtResultMapGenerator();
			initializeAndExecuteGenerator(elementGenerator, parentElement, introspectedTable);
		}
	}

	protected void initializeAndExecuteGenerator(AbstractXmlElementGenerator elementGenerator, XmlElement parentElement,
			IntrospectedTable introspectedTable) {

		elementGenerator.setContext(context);
		elementGenerator.setIntrospectedTable(introspectedTable);
		elementGenerator.setProgressCallback(null);
		elementGenerator.setWarnings(new ArrayList<String>());
		elementGenerator.addElements(parentElement);
	}

	@Override
	public boolean sqlMapDocumentGenerated(Document document, IntrospectedTable introspectedTable) {
		List<XmlElement> elements = elementsToAdd.get(introspectedTable.getFullyQualifiedTable());
		System.out.println("-------------------" + elements);
		if (elements != null) {
			System.out.println("size:" + elementsToAdd.size());
			for (XmlElement element : elements) {
				document.getRootElement().addElement(element);

			}
		}

		return true;
	}

	@Override
	public boolean sqlMapGenerated(GeneratedXmlFile sqlMap, IntrospectedTable introspectedTable) {
		try {
			java.lang.reflect.Field field =  sqlMap.getClass().getDeclaredField("isMergeable");
			field.setAccessible(true);
			field.setBoolean(sqlMap,false);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return true;
	}

}
